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
package eu.stratosphere.pact.testing.ioformats;

import java.io.DataOutputStream;
import java.io.IOException;

import eu.stratosphere.nephele.configuration.Configuration;
import eu.stratosphere.pact.common.io.FileOutputFormat;
import eu.stratosphere.pact.common.type.PactRecord;

/**
 * Stores the key/value pairs in a native format which is deserialable without configuration. Currently, the assumption
 * is that all keys and all values are of the same respective type.
 * 
 * @author Arvid Heise
 * @see SequentialInputFormat
 */
public class SequentialOutputFormat extends FileOutputFormat {
	private DataOutputStream dataOutputStream;

	public SequentialOutputFormat() {
	}

	@Override
	public void close() throws IOException {
		this.dataOutputStream.close();
	}

	@Override
	public void configure(final Configuration parameters) {
		super.configure(parameters);
	}

	@Override
	public void open(int taskNumber) throws IOException {
		super.open(taskNumber);
		this.dataOutputStream = new DataOutputStream(this.stream);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.pact.common.io.OutputFormat#writeRecord(eu.stratosphere.pact.common.type.PactRecord)
	 */
	@Override
	public void writeRecord(PactRecord record) throws IOException {
		record.write(this.dataOutputStream);
	}
}