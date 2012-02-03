package eu.stratosphere.pact.example.pigmix;

import java.util.List;

import eu.stratosphere.pact.common.contract.FileDataSink;
import eu.stratosphere.pact.common.contract.FileDataSource;
import eu.stratosphere.pact.common.contract.MapContract;
import eu.stratosphere.pact.common.contract.Order;
import eu.stratosphere.pact.common.io.RecordOutputFormat;
import eu.stratosphere.pact.common.io.TextInputFormat;
import eu.stratosphere.pact.common.plan.Plan;
import eu.stratosphere.pact.common.plan.PlanAssembler;
import eu.stratosphere.pact.common.stubs.Collector;
import eu.stratosphere.pact.common.stubs.MapStub;
import eu.stratosphere.pact.common.type.PactRecord;
import eu.stratosphere.pact.common.type.base.PactDouble;
import eu.stratosphere.pact.common.type.base.PactInteger;
import eu.stratosphere.pact.common.type.base.PactString;

public class L10 implements PlanAssembler{

	public static class ProjectPageViews extends MapStub
	{
		private final PactRecord rec = new PactRecord();

		@Override
		public void map(PactRecord record, Collector out) throws Exception
		{
			PactString str = record.getField(0, PactString.class);
			if (str.length() > 0) {
				List<PactString> fields = Library.splitLine(str, '');

				MultiOrderKey mulitOrder = new MultiOrderKey(fields.get(3).getValue(), fields.get(6).getValue(), fields.get(2).getValue());
				rec.setField(0, mulitOrder);
				out.collect(rec);
			}
		}	
	}

	/* (non-Javadoc)
	 * @see eu.stratosphere.pact.common.plan.PlanAssembler#getPlan(java.lang.String[])
	 */
	@Override
	public Plan getPlan(String... args)
	{
		final int parallelism = args.length > 0 ? Integer.parseInt(args[0]) : 1;
		final String pageViewsFile = "hdfs://cloud-7.dima.tu-berlin.de:40010/pigmix/pigmix625k/page_views";

		FileDataSource pageViews = new FileDataSource(TextInputFormat.class, pageViewsFile, "Read PageViews");
		pageViews.setDegreeOfParallelism(parallelism);


		MapContract projectPageViews = new MapContract(ProjectPageViews.class, pageViews, "Project Page Views");
		projectPageViews.setDegreeOfParallelism(parallelism);


		FileDataSink sink = new FileDataSink(RecordOutputFormat.class, "hdfs://cloud-7.dima.tu-berlin.de:40010/pigmix/result_L10", projectPageViews, "Result");
		sink.setDegreeOfParallelism(parallelism);
		sink.setGlobalOrder(Order.ASCENDING);
		sink.getParameters().setInteger(RecordOutputFormat.NUM_FIELDS_PARAMETER, 1);
		sink.getParameters().setClass(RecordOutputFormat.FIELD_TYPE_PARAMETER_PREFIX + 0, MultiOrderKey.class);

		Plan plan = new Plan(sink, "L10 order by multiple fields");
		return plan;
	}
}
