package eu.stratosphere.nephele.taskmanager.bytebuffered;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import eu.stratosphere.nephele.event.task.AbstractEvent;
import eu.stratosphere.nephele.io.channels.Buffer;
import eu.stratosphere.nephele.io.channels.ChannelID;
import eu.stratosphere.nephele.io.channels.bytebuffered.AbstractByteBufferedOutputChannel;
import eu.stratosphere.nephele.io.channels.bytebuffered.BufferPairResponse;
import eu.stratosphere.nephele.io.channels.bytebuffered.ByteBufferedChannelActivateEvent;
import eu.stratosphere.nephele.io.channels.bytebuffered.ByteBufferedOutputChannelBroker;
import eu.stratosphere.nephele.jobgraph.JobID;
import eu.stratosphere.nephele.taskmanager.transferenvelope.TransferEnvelope;

final class OutputChannelContext implements ByteBufferedOutputChannelBroker, ChannelContext {

	/**
	 * The static object used for logging.
	 */
	private static final Log LOG = LogFactory.getLog(OutputChannelContext.class);

	/**
	 * The byte buffered output channel this context belongs to.
	 */
	private final AbstractByteBufferedOutputChannel<?> byteBufferedOutputChannel;

	/**
	 * The output gate context associated with this context.
	 */
	private final OutputGateContext outputGateContext;

	/**
	 * Points to the {@link TransferEnvelope} object that will be passed to the framework upon
	 * the next <code>releaseWriteBuffers</code> call.
	 */
	private TransferEnvelope outgoingTransferEnvelope = null;

	/**
	 * Indicates whether the receiver of an envelope is currently running.
	 */
	private volatile boolean isReceiverRunning = false;

	private Queue<TransferEnvelope> queuedOutgoingEnvelopes;

	/**
	 * The sequence number for the next {@link TransferEnvelope} to be created.
	 */
	private int sequenceNumber = 0;

	OutputChannelContext(final OutputGateContext outputGateContext,
			final AbstractByteBufferedOutputChannel<?> byteBufferedOutputChannel, final boolean isReceiverRunning) {

		this.outputGateContext = outputGateContext;
		this.byteBufferedOutputChannel = byteBufferedOutputChannel;
		this.byteBufferedOutputChannel.setByteBufferedOutputChannelBroker(this);
		this.isReceiverRunning = isReceiverRunning;

		this.queuedOutgoingEnvelopes = new ArrayDeque<TransferEnvelope>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BufferPairResponse requestEmptyWriteBuffers() throws InterruptedException, IOException {

		if (this.outgoingTransferEnvelope == null) {
			this.outgoingTransferEnvelope = createNewOutgoingTransferEnvelope();
		} else {
			if (this.outgoingTransferEnvelope.getBuffer() != null) {
				LOG.error("Channel " + this.byteBufferedOutputChannel.getID()
					+ "'s transfer envelope already has a buffer attached");
				return null;
			}
		}

		final int uncompressedBufferSize = calculateBufferSize();

		// TODO: This implementation breaks compression, we have to fix it later
		final Buffer buffer = this.outputGateContext.requestEmptyBufferBlocking(uncompressedBufferSize);
		final BufferPairResponse bufferResponse = new BufferPairResponse(null, buffer);

		// Put the buffer into the transfer envelope
		this.outgoingTransferEnvelope.setBuffer(bufferResponse.getUncompressedDataBuffer());

		return bufferResponse;
	}

	/**
	 * Creates a new {@link TransferEnvelope} object. The method assigns
	 * and increases the sequence number. Moreover, it will look up the list of receivers for this transfer envelope.
	 * This method will block until the lookup is completed.
	 * 
	 * @return a new {@link TransferEnvelope} object containing the correct sequence number and receiver list
	 */
	private TransferEnvelope createNewOutgoingTransferEnvelope() {

		final TransferEnvelope transferEnvelope = new TransferEnvelope(this.sequenceNumber++,
			this.byteBufferedOutputChannel.getJobID(),
			this.byteBufferedOutputChannel.getID());

		return transferEnvelope;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void releaseWriteBuffers() throws IOException, InterruptedException {

		if (this.outgoingTransferEnvelope == null) {
			LOG.error("Cannot find transfer envelope for channel with ID " + this.byteBufferedOutputChannel.getID());
			return;
		}

		// Consistency check
		if (this.outgoingTransferEnvelope.getBuffer() == null) {
			LOG.error("Channel " + this.byteBufferedOutputChannel.getID() + " has no buffer attached");
			return;
		}

		// Finish the write phase of the buffer
		try {
			this.outgoingTransferEnvelope.getBuffer().finishWritePhase();
		} catch (final IOException ioe) {
			this.byteBufferedOutputChannel.reportIOException(ioe);
		}

		if (!this.isReceiverRunning) {

			final Buffer memBuffer = this.outgoingTransferEnvelope.getBuffer();
			final Buffer fileBuffer = this.outputGateContext.getFileBuffer(memBuffer.size());
			memBuffer.copyToBuffer(fileBuffer);
			this.outgoingTransferEnvelope.setBuffer(fileBuffer);
			this.queuedOutgoingEnvelopes.add(this.outgoingTransferEnvelope);
			this.outgoingTransferEnvelope = null;
			memBuffer.recycleBuffer();

			return;
		}

		while (!this.queuedOutgoingEnvelopes.isEmpty()) {
			this.outputGateContext.processEnvelope(this.queuedOutgoingEnvelopes.poll());
		}

		this.outputGateContext.processEnvelope(this.outgoingTransferEnvelope);
		this.outgoingTransferEnvelope = null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void transferEventToInputChannel(final AbstractEvent event) throws IOException, InterruptedException {

		if (this.outgoingTransferEnvelope != null) {
			this.outgoingTransferEnvelope.addEvent(event);
		} else {

			final TransferEnvelope ephemeralTransferEnvelope = createNewOutgoingTransferEnvelope();
			ephemeralTransferEnvelope.addEvent(event);

			if (!this.isReceiverRunning) {

				this.queuedOutgoingEnvelopes.add(ephemeralTransferEnvelope);

				return;
			}

			while (!this.queuedOutgoingEnvelopes.isEmpty()) {
				this.outputGateContext.processEnvelope(this.queuedOutgoingEnvelopes.poll());
			}

			this.outputGateContext.processEnvelope(ephemeralTransferEnvelope);
		}
	}

	/**
	 * Calculates the recommended size of the next buffer to be
	 * handed to the attached channel object in bytes.
	 * 
	 * @return the recommended size of the next buffer in bytes
	 */
	private int calculateBufferSize() {

		// TODO: Include latency considerations
		return this.outputGateContext.getMaximumBufferSize();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isInputChannel() {

		return false;
	}

	/**
	 * Called by the framework to report events to
	 * the attached channel object.
	 * 
	 * @param abstractEvent
	 *        the event to be reported
	 */
	void processEvent(AbstractEvent abstractEvent) {

		this.byteBufferedOutputChannel.processEvent(abstractEvent);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reportIOException(IOException ioe) {

		this.byteBufferedOutputChannel.reportIOException(ioe);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ChannelID getChannelID() {

		return this.byteBufferedOutputChannel.getID();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ChannelID getConnectedChannelID() {

		return this.byteBufferedOutputChannel.getConnectedChannelID();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JobID getJobID() {

		return this.byteBufferedOutputChannel.getJobID();
	}

	@Override
	public void queueTransferEnvelope(TransferEnvelope transferEnvelope) {

		if (transferEnvelope.getBuffer() != null) {
			LOG.error("Transfer envelope for output channel has buffer attached");
		}

		Iterator<AbstractEvent> it = transferEnvelope.getEventList().iterator();
		while (it.hasNext()) {

			final AbstractEvent event = it.next();

			if (event instanceof ByteBufferedChannelActivateEvent) {
				this.isReceiverRunning = true;
			} else {
				this.byteBufferedOutputChannel.processEvent(event);
			}
		}
	}
}
