package eu.stratosphere.nephele.taskmanager.bytebuffered;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import eu.stratosphere.nephele.io.OutputGate;
import eu.stratosphere.nephele.io.channels.Buffer;
import eu.stratosphere.nephele.io.channels.BufferFactory;
import eu.stratosphere.nephele.io.channels.ChannelType;
import eu.stratosphere.nephele.io.channels.FileBufferManager;
import eu.stratosphere.nephele.taskmanager.checkpointing.EphemeralCheckpoint;
import eu.stratosphere.nephele.taskmanager.transferenvelope.TransferEnvelope;
import eu.stratosphere.nephele.taskmanager.transferenvelope.TransferEnvelopeDispatcher;

final class OutputGateContext {

	private final TaskContext taskContext;

	private final OutputGate<?> outputGate;

	private final FileBufferManager fileBufferManager;

	private final EphemeralCheckpoint ephemeralCheckpoint;

	private final Set<OutputChannelContext> channelWithMemoryBuffers;

	/**
	 * The dispatcher for received transfer envelopes.
	 */
	private final TransferEnvelopeDispatcher transferEnvelopeDispatcher;

	OutputGateContext(final TaskContext taskContext, final OutputGate<?> outputGate,
			final TransferEnvelopeDispatcher transferEnvelopeDispatcher, final FileBufferManager fileBufferManager) {

		this.taskContext = taskContext;
		this.outputGate = outputGate;

		this.transferEnvelopeDispatcher = transferEnvelopeDispatcher;
		this.fileBufferManager = fileBufferManager;

		this.ephemeralCheckpoint = new EphemeralCheckpoint(this.outputGate.getGateID(),
			(outputGate.getChannelType() == ChannelType.FILE) ? false : true, this.fileBufferManager);

		this.channelWithMemoryBuffers = new HashSet<OutputChannelContext>();
	}

	Buffer requestEmptyBufferBlocking(final OutputChannelContext caller, final int minimumSizeOfBuffer)
			throws IOException, InterruptedException {

		Buffer buffer = this.taskContext.requestEmptyBuffer(minimumSizeOfBuffer, 0);
		if (buffer == null) {

			// We are out of byte buffers
			if (!this.ephemeralCheckpoint.isDecided()) {
				this.ephemeralCheckpoint.destroy();
				// this.ephemeralCheckpoint.write();
			}

			flushFullestBuffer();

			buffer = this.taskContext.requestEmptyBufferBlocking(minimumSizeOfBuffer, 0);
		}

		if (buffer.isBackedByMemory()) {
			this.channelWithMemoryBuffers.add(caller);
		}

		return buffer;
	}

	private void flushFullestBuffer() throws InterruptedException {

		// Iterator through list of channels with buffers
		final Iterator<OutputChannelContext> it = this.channelWithMemoryBuffers.iterator();
		int minRemaining = -1;
		OutputChannelContext minContext = null;

		while (it.hasNext()) {

			final OutputChannelContext context = it.next();

			final int remaining = context.getRemainingBytesOfWorkingBuffer();

			if (remaining > 0) {

				if (minContext == null) {
					minContext = context;
					minRemaining = remaining;
				} else {
					if (remaining < minRemaining) {
						minRemaining = remaining;
						minContext = context;
					}
				}
			}
		}

		if (minContext != null) {
			try {
				minContext.flush();
			} catch (IOException ioe) {
				minContext.reportIOException(ioe);
			}
		}
	}

	int getMaximumBufferSize() {

		return this.taskContext.getMaximumBufferSize();
	}

	/**
	 * Called by the attached output channel wrapper to forward a {@link TransferEnvelope} object
	 * to its final destination. Within this method the provided transfer envelope is possibly also
	 * forwarded to the assigned ephemeral checkpoint.
	 * 
	 * @param outgoingTransferEnvelope
	 *        the transfer envelope to be forwarded
	 * @throws IOException
	 *         thrown if an I/O error occurs while processing the envelope
	 * @throws InterruptedException
	 *         thrown if the thread is interrupted while waiting for the envelope to be processed
	 */
	void processEnvelope(final OutputChannelContext caller, final TransferEnvelope outgoingTransferEnvelope)
			throws IOException,
			InterruptedException {

		/*
		 * if (!this.ephemeralCheckpoint.isDiscarded()) {
		 * final TransferEnvelope dup = outgoingTransferEnvelope.duplicate();
		 * this.ephemeralCheckpoint.addTransferEnvelope(dup);
		 * }
		 */

		final Buffer buffer = outgoingTransferEnvelope.getBuffer();
		if (buffer != null) {
			this.channelWithMemoryBuffers.remove(caller);
		}

		this.transferEnvelopeDispatcher.processEnvelopeFromOutputChannel(outgoingTransferEnvelope);
	}

	Buffer getFileBuffer(final int bufferSize) throws IOException {

		return BufferFactory.createFromFile(bufferSize, this.outputGate.getGateID(), this.fileBufferManager);
	}
}
