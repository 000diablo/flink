package eu.stratosphere.pact.runtime.task;

import static eu.stratosphere.pact.common.util.ReflectionUtil.getTemplateType1;
import static eu.stratosphere.pact.common.util.ReflectionUtil.getTemplateType2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import eu.stratosphere.nephele.configuration.Configuration;
import eu.stratosphere.pact.common.stub.Stub;
import eu.stratosphere.pact.common.type.KeyValuePair;
import eu.stratosphere.pact.common.type.base.PactInteger;
import eu.stratosphere.pact.runtime.test.util.RegularlyGeneratedInputGenerator;
import eu.stratosphere.pact.runtime.test.util.TaskTestBase;

public class TempTaskTest extends TaskTestBase {

	private static final Log LOG = LogFactory.getLog(TempTaskTest.class);
	
	List<KeyValuePair<PactInteger,PactInteger>> outList;
		
	@Test
	public void testTempTask() {

		Logger.getRootLogger().setLevel(Level.DEBUG);
		
		int keyCnt = 1024;
		int valCnt = 4;
		
		outList = new ArrayList<KeyValuePair<PactInteger,PactInteger>>();
		
		super.initEnvironment(1024*1024*1);
		super.addInput(new RegularlyGeneratedInputGenerator(keyCnt, valCnt));
		super.addOutput(outList);
		
		TempTask testTask = new TempTask();
		super.getTaskConfig().setIOBufferSize(1);
		
		super.registerTask(testTask, PrevStub.class);
		
		try {
			testTask.invoke();
		} catch (Exception e) {
			LOG.debug(e);
		}
		
		Assert.assertTrue(outList.size() == keyCnt*valCnt);
		
	}
	
	public static class PrevStub extends Stub<PactInteger,PactInteger> {

		@Override
		public void close() throws IOException {
		}

		@Override
		public void configure(Configuration parameters) {
		}

		@Override
		protected void initTypes() {
			super.ok = getTemplateType1(getClass());
			super.ov = getTemplateType2(getClass());
		}

		@Override
		public void open() throws IOException {
		}

				
	}
		
}
