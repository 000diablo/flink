/***********************************************************************************************************************
 *
 * Copyright (C) 2010 by the Stratosphere project (http://stratosphere.eu)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 **********************************************************************************************************************/

package eu.stratosphere.nephele.taskmanager.bufferprovider;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Queue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import eu.stratosphere.nephele.io.channels.Buffer;
import eu.stratosphere.nephele.io.channels.BufferFactory;

public final class LocalBufferCache implements BufferProvider {

	private final static Log LOG = LogFactory.getLog(LocalBufferCache.class);
	
	private final GlobalBufferPool globalBufferPool;

	private final int maximumBufferSize;

	private int designatedNumberOfBuffers;

	private int requestedNumberOfBuffers = 0;

	private final Queue<ByteBuffer> buffers = new ArrayDeque<ByteBuffer>();

	public LocalBufferCache(final int designatedNumberOfBuffers) {

		this.globalBufferPool = GlobalBufferPool.getInstance();
		this.maximumBufferSize = this.globalBufferPool.getMaximumBufferSize();
		this.designatedNumberOfBuffers = designatedNumberOfBuffers;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Buffer requestEmptyBuffer(final int minimumSizeOfBuffer) throws IOException {

		try {
			return requestBufferInternal(minimumSizeOfBuffer, false);
		} catch(InterruptedException e) {
			LOG.error("Caught unexpected InterruptedException");
		}
		
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Buffer requestEmptyBufferBlocking(int minimumSizeOfBuffer) throws IOException, InterruptedException {

		return requestBufferInternal(minimumSizeOfBuffer, true);
	}

	
	private Buffer requestBufferInternal(final int minimumSizeOfBuffer, final boolean block) throws InterruptedException {

		if (minimumSizeOfBuffer > this.maximumBufferSize) {
			throw new IllegalArgumentException("Buffer of " + minimumSizeOfBuffer
				+ " bytes is requested, but maximum buffer size is " + this.maximumBufferSize);
		}

		synchronized (this.buffers) {

			// Check if the number of cached buffers matches the number of designated buffers
			if (this.requestedNumberOfBuffers < this.designatedNumberOfBuffers) {

				while (this.requestedNumberOfBuffers < this.designatedNumberOfBuffers) {

					final ByteBuffer buffer = this.globalBufferPool.lockGlobalBuffer();
					if (buffer == null) {
						break;
					}

					this.buffers.add(buffer);
				}

			} else if (this.requestedNumberOfBuffers > this.designatedNumberOfBuffers) {

				while (this.requestedNumberOfBuffers < this.designatedNumberOfBuffers) {

					final ByteBuffer buffer = this.buffers.poll();
					if (buffer == null) {
						break;
					}

					this.globalBufferPool.releaseGlobalBuffer(buffer);
				}

			}

			while(this.buffers.isEmpty()) {
				
				if(block) {
					this.buffers.wait();
				} else {
					return null;
				}
			}
			
			final ByteBuffer byteBuffer = this.buffers.poll();
			
			return BufferFactory.createFromMemory(minimumSizeOfBuffer, byteBuffer, this.buffers);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMaximumBufferSize() {

		return this.maximumBufferSize;
	}

	/**
	 * Sets the designated number of buffers for this local buffer cache.
	 * 
	 * @param designatedNumberOfBuffers
	 *        the designated number of buffers for this local buffer cache
	 */
	public void setDesignatedNumberOfBuffers(final int designatedNumberOfBuffers) {

		synchronized (this.buffers) {
			this.designatedNumberOfBuffers = designatedNumberOfBuffers;
		}
	}

}
