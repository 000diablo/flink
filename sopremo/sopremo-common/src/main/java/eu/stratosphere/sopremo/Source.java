package eu.stratosphere.sopremo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import eu.stratosphere.pact.common.contract.FileDataSource;
import eu.stratosphere.pact.common.io.FileInputFormat;
import eu.stratosphere.pact.common.plan.PactModule;
import eu.stratosphere.sopremo.expressions.ArrayCreation;
import eu.stratosphere.sopremo.expressions.EvaluationExpression;
import eu.stratosphere.sopremo.io.JsonGenerator;
import eu.stratosphere.sopremo.io.JsonProcessingException;
import eu.stratosphere.sopremo.pact.JsonInputFormat;
import eu.stratosphere.sopremo.pact.SopremoUtil;
import eu.stratosphere.sopremo.type.IJsonNode;
import eu.stratosphere.sopremo.type.NullNode;

@InputCardinality(min = 0, max = 0)
public class Source extends ElementaryOperator<Source> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4321371118396025441L;

	private String inputPath;

	private EvaluationExpression adhocExpression;

	private Class<? extends FileInputFormat> inputFormat;

	public Source(final EvaluationExpression adhocValue) {
		this.adhocExpression = adhocValue;
		this.inputFormat = JsonInputFormat.class;
	}

	public Source(final Class<? extends FileInputFormat> inputformat,
			final String inputPath) {
		this.inputPath = inputPath;
		this.inputFormat = inputformat;
	}

	public Source(final String inputPath) {
		this(JsonInputFormat.class, inputPath);
	}

	public Source() {
		this(new ArrayCreation());
	}

	public String getInputPath() {
		return this.inputPath;
	}

	public void setInputPath(final String inputPath) {
		if (inputPath == null)
			throw new NullPointerException("inputPath must not be null");

		this.adhocExpression = null;
		this.inputPath = inputPath;
	}

	public Class<? extends FileInputFormat> getInputFormat() {
		return this.inputFormat;
	}

	public void setInputFormat(final Class<? extends FileInputFormat> inputFormat) {
		if (inputFormat == null)
			throw new NullPointerException("inputFormat must not be null");

		this.inputFormat = inputFormat;
	}

	public Map<String, Object> getParameters() {
		return this.parameters;
	}

	public void setAdhocExpression(final EvaluationExpression adhocExpression) {
		if (adhocExpression == null)
			throw new NullPointerException("adhocExpression must not be null");

		this.inputPath = null;
		this.adhocExpression = adhocExpression;
	}

	@Override
	public PactModule asPactModule(final EvaluationContext context) {
		String inputPath = this.inputPath, name = this.inputPath;
		if (this.isAdhoc())
			try {
				final File tempFile = File.createTempFile("Adhoc", "source");
				this.writeValues(tempFile);
				inputPath = "file://localhost" + tempFile.getAbsolutePath();
				SopremoUtil.LOG.info("temp file " + inputPath);
				name = "Adhoc";
			} catch (final IOException e) {
				throw new IllegalStateException("Cannot create adhoc source", e);
			}
		final PactModule pactModule = new PactModule(this.toString(), 0, 1);
		final FileDataSource contract = new FileDataSource(this.inputFormat, inputPath, name);
		if (this.inputFormat == JsonInputFormat.class)
			contract.setDegreeOfParallelism(1);

		for (final Entry<String, Object> parameter : this.parameters.entrySet())
			if (parameter.getValue() instanceof Serializable)
				SopremoUtil
					.serialize(contract.getParameters(), parameter.getKey(), (Serializable) parameter.getValue());
		pactModule.getOutput(0).setInput(contract);
		// pactModule.setInput(0, contract);
		return pactModule;
	}

	public boolean isAdhoc() {
		return this.adhocExpression != null;
	}

	private final Map<String, Object> parameters = new HashMap<String, Object>();

	public void setParameter(final String key, final Object value) {
		this.parameters.put(key, value);
	}

	private void writeValues(final File tempFile) throws IOException, JsonProcessingException {
		final JsonGenerator writer = new JsonGenerator(tempFile);
		writer.writeTree(this.getAdhocValues());
		writer.close();
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		final Source other = (Source) obj;
		return (this.inputPath == null ? other.inputFormat == null : this.inputPath.equals(other.inputPath))
			&& (this.adhocExpression == null ? this.adhocExpression == null : this.adhocExpression
				.equals(other.adhocExpression));
	}

	public EvaluationExpression getAdhocExpression() {
		return this.adhocExpression;
	}

	public IJsonNode getAdhocValues() {
		if (!this.isAdhoc())
			throw new IllegalStateException();
		return this.getAdhocExpression().evaluate(NullNode.getInstance(), new EvaluationContext());
	}

	public String getInputName() {
		return this.inputPath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (this.adhocExpression == null ? 0 : this.adhocExpression.hashCode());
		result = prime * result + (this.inputPath == null ? 0 : this.inputPath.hashCode());
		return result;
	}

	@Override
	public String toString() {
		if (this.isAdhoc())
			return "Source [" + this.adhocExpression + "]";

		return "Source [" + this.inputPath + "]";
	}
}
