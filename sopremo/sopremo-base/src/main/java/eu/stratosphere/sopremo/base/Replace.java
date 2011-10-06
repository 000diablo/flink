package eu.stratosphere.sopremo.base;

import java.util.Iterator;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.NullNode;

import eu.stratosphere.sopremo.CompositeOperator;
import eu.stratosphere.sopremo.ElementaryOperator;
import eu.stratosphere.sopremo.EvaluationContext;
import eu.stratosphere.sopremo.EvaluationException;
import eu.stratosphere.sopremo.InputCardinality;
import eu.stratosphere.sopremo.JsonUtil;
import eu.stratosphere.sopremo.Name;
import eu.stratosphere.sopremo.Operator;
import eu.stratosphere.sopremo.Property;
import eu.stratosphere.sopremo.SopremoModule;
import eu.stratosphere.sopremo.StreamArrayNode;
import eu.stratosphere.sopremo.expressions.ArrayAccess;
import eu.stratosphere.sopremo.expressions.EvaluationExpression;
import eu.stratosphere.sopremo.expressions.JsonStreamExpression;
import eu.stratosphere.sopremo.expressions.UnaryExpression;
import eu.stratosphere.sopremo.pact.JsonCollector;
import eu.stratosphere.sopremo.pact.PactJsonObject;
import eu.stratosphere.sopremo.pact.PactJsonObject.Key;
import eu.stratosphere.sopremo.pact.SopremoCoGroup;
import eu.stratosphere.sopremo.pact.SopremoMatch;
import eu.stratosphere.sopremo.pact.SopremoReduce;

@InputCardinality(min = 2, max = 2)
@Name(verb = "replace")
public class Replace extends CompositeOperator<Replace> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5213470669940261166L;

	private EvaluationExpression replaceExpression = EvaluationExpression.VALUE;

	public final static EvaluationExpression FILTER_RECORDS = new EvaluationExpression() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -8218311569919645735L;

		@Override
		public JsonNode evaluate(JsonNode node, EvaluationContext context) {
			throw new EvaluationException("Tag expression");
		}

		private Object readResolve() {
			return FILTER_RECORDS;
		}

		@Override
		protected void toString(StringBuilder builder) {
			builder.append("<filter>");
		};
	};

	private EvaluationExpression dictionaryKeyExtraction = EvaluationExpression.KEY,
			dictionaryValueExtraction = EvaluationExpression.VALUE,
			defaultExpression = FILTER_RECORDS;

	private boolean arrayElementsReplacement = false;

	public JsonStreamExpression getDictionary() {
		return new JsonStreamExpression(getInput(1));
	}

	public Replace withDictionary(JsonStreamExpression dictionary) {
		setDictionary(dictionary);
		return this;
	}

	@Property
	@Name(noun = "dictionary", preposition = "with")
	public void setDictionary(JsonStreamExpression dictionary) {
		if (dictionary == null)
			throw new NullPointerException("dictionary must not be null");

		this.setInput(1, dictionary.getStream());
	}

	@Override
	public SopremoModule asElementaryOperators() {
		final SopremoModule sopremoModule = new SopremoModule(this.getName(), 2, 1);
		final Projection right = new Projection();
		right.setKeyTransformation(this.dictionaryKeyExtraction);
		right.setValueTransformation(this.dictionaryValueExtraction);
		right.setInputs(sopremoModule.getInput(1));

		if (this.arrayElementsReplacement) {
			final ArraySplit arraySplit = new ArraySplit().
				withArrayPath(this.replaceExpression).
				withKeyProjection(new ArrayAccess(0)).
				withValueProjection(new ArrayAccess(1, 2)).
				withInputs(sopremoModule.getInput(0));

			final Operator<?> replacedElements = this.defaultExpression == FILTER_RECORDS ?
				new ElementStrictReplace().withInputs(arraySplit, right) :
				new ElementReplaceWithDefault().withDefaultExpression(this.defaultExpression).withInputs(arraySplit,
					right);

			final AssembleArray arrayDictionary = new AssembleArray().withInputs(replacedElements);

			final Replace arrayLookup = new Replace();
			arrayLookup.setInputs(sopremoModule.getInput(0), arrayDictionary);
			arrayLookup.setReplaceExpression(this.replaceExpression);
			Selection emptyArrays = new Selection().
				withCondition(new UnaryExpression(this.replaceExpression, true)).
				withInputs(sopremoModule.getInput(0));
			sopremoModule.getOutput(0).setInput(0, new UnionAll().withInputs(arrayLookup, emptyArrays));
		} else {
			final Projection left = new Projection().
				withKeyTransformation(this.replaceExpression).
				withInputs(sopremoModule.getInput(0));
			if (this.defaultExpression == FILTER_RECORDS)
				sopremoModule.getOutput(0).setInput(0,
					new StrictReplace().withInputKeyExtractor(this.replaceExpression).withInputs(left, right));
			else
				sopremoModule.getOutput(0).setInput(0,
					new ReplaceWithDefaultValue().
						withInputKeyExtractor(this.replaceExpression).
						withDefaultExpression(this.defaultExpression).
						withInputs(left, right));
		}
		return sopremoModule;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Replace other = (Replace) obj;
		return this.arrayElementsReplacement == other.arrayElementsReplacement &&
			this.defaultExpression.equals(other.defaultExpression) &&
			this.dictionaryKeyExtraction.equals(other.dictionaryKeyExtraction) &&
			this.dictionaryValueExtraction.equals(other.dictionaryValueExtraction) &&
			this.replaceExpression.equals(other.replaceExpression);
	}

	public EvaluationExpression getDefaultExpression() {
		return this.defaultExpression;
	}

	public EvaluationExpression getDictionaryKeyExtraction() {
		return this.dictionaryKeyExtraction;
	}

	public EvaluationExpression getDictionaryValueExtraction() {
		return this.dictionaryValueExtraction;
	}

	public EvaluationExpression getReplaceExpression() {
		return this.replaceExpression;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (this.arrayElementsReplacement ? 1231 : 1237);
		result = prime * result + this.defaultExpression.hashCode();
		result = prime * result + this.dictionaryKeyExtraction.hashCode();
		result = prime * result + this.dictionaryValueExtraction.hashCode();
		result = prime * result + this.replaceExpression.hashCode();
		return result;
	}

	@Override
	public String toString() {
		if (isArrayElementsReplacement())
			return String.format("%s all %s default %s", getName(), getReplaceExpression(), getDefaultExpression());
		return String.format("%s %s default %s", getName(), getReplaceExpression(), getDefaultExpression());
	}

	public boolean isArrayElementsReplacement() {
		return this.arrayElementsReplacement;
	}

	@Property(flag = true)
	@Name(adjective = "all")
	public void setArrayElementsReplacement(boolean replaceElementsInArray) {
		this.arrayElementsReplacement = replaceElementsInArray;
	}

	@Property
	@Name(noun = "default")
	public void setDefaultExpression(EvaluationExpression defaultExpression) {
		if (defaultExpression == null)
			throw new NullPointerException("defaultExpression must not be null");

		this.defaultExpression = defaultExpression;
	}

	public void setDictionaryKeyExtraction(EvaluationExpression dictionaryKeyExtraction) {
		if (dictionaryKeyExtraction == null)
			throw new NullPointerException("dictionaryKeyExtraction must not be null");

		this.dictionaryKeyExtraction = dictionaryKeyExtraction;
	}

	public void setDictionaryValueExtraction(EvaluationExpression dictionaryValueExtraction) {
		if (dictionaryValueExtraction == null)
			throw new NullPointerException("dictionaryValueExtraction must not be null");

		this.dictionaryValueExtraction = dictionaryValueExtraction;
	}

	@Property()
	@Name(preposition = "on")
	public void setReplaceExpression(EvaluationExpression inputKeyExtract) {
		if (inputKeyExtract == null)
			throw new NullPointerException("inputKeyExtract must not be null");

		this.replaceExpression = inputKeyExtract;
	}

	public Replace withArrayElementsReplacement(boolean replaceArrays) {
		this.setArrayElementsReplacement(replaceArrays);
		return this;
	}

	public Replace withDictionaryKeyExtraction(EvaluationExpression dictionaryKeyExtraction) {
		this.setDictionaryKeyExtraction(dictionaryKeyExtraction);
		return this;
	}

	public Replace withDictionaryValueExtraction(EvaluationExpression dictionaryValueExtraction) {
		this.setDictionaryValueExtraction(dictionaryValueExtraction);
		return this;
	}

	public Replace withReplaceExpression(EvaluationExpression inputKeyExtract) {
		this.setReplaceExpression(inputKeyExtract);
		return this;
	}

	public static class AssembleArray extends ElementaryOperator<AssembleArray> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 7334161941683036846L;

		public static class Implementation extends
				SopremoReduce<Key, PactJsonObject, Key, PactJsonObject> {
			@Override
			protected void reduce(JsonNode key, StreamArrayNode values, JsonCollector out) {
				JsonNode[] array = new JsonNode[key.size()];
				int replacedCount = 0;
				for (JsonNode value : values) {
					int index = value.get(0).getIntValue();
					JsonNode element = value.get(1);
					array[index] = element;
					replacedCount++;
				}

				// all values replaced
				if (replacedCount == array.length)
					out.collect(key, JsonUtil.asArray(array));
			}
		}
	}

	@InputCardinality(min = 2, max = 2)
	public static class ElementReplaceWithDefault extends ElementaryOperator<ElementReplaceWithDefault> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 7334161941683036846L;

		private EvaluationExpression defaultExpression = FILTER_RECORDS;

		public void setDefaultExpression(EvaluationExpression defaultExpression) {
			if (defaultExpression == null)
				throw new NullPointerException("defaultExpression must not be null");

			this.defaultExpression = defaultExpression;
		}

		public ElementReplaceWithDefault withDefaultExpression(EvaluationExpression defaultExpression) {
			this.setDefaultExpression(defaultExpression);
			return this;
		}

		public EvaluationExpression getDefaultExpression() {
			return this.defaultExpression;
		}

		public static class Implementation extends
				SopremoCoGroup<Key, PactJsonObject, PactJsonObject, Key, PactJsonObject> {

			private EvaluationExpression defaultExpression;

			@Override
			protected void coGroup(JsonNode key, StreamArrayNode values1, StreamArrayNode values2, JsonCollector out) {

				final Iterator<JsonNode> replaceValueIterator = values2.iterator();
				JsonNode replaceValue = replaceValueIterator.hasNext() ? replaceValueIterator.next() : null;

				final Iterator<JsonNode> valueIterator = values1.iterator();
				final EvaluationContext context = this.getContext();
				while (valueIterator.hasNext()) {
					JsonNode value = valueIterator.next();
					final JsonNode index = value.get(0);
					JsonNode replacement = replaceValue != null ? replaceValue :
						this.defaultExpression.evaluate(value.get(1).get(index.getIntValue()), context);
					out.collect(value.get(1), JsonUtil.asArray(index, replacement));
				}
			}
		}
	}

	@InputCardinality(min = 2, max = 2)
	public static class ElementStrictReplace extends ElementaryOperator<ElementStrictReplace> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 7334161941683036846L;

		public static class Implementation extends
				SopremoMatch<Key, PactJsonObject, PactJsonObject, Key, PactJsonObject> {
			@Override
			protected void match(final JsonNode key, final JsonNode value1, final JsonNode value2,
					final JsonCollector out) {
				out.collect(value1.get(1), JsonUtil.asArray(value1.get(0), value2));
			}
		}
	}

	@InputCardinality(min = 2, max = 2)
	public static class ReplaceWithDefaultValue extends ElementaryOperator<ReplaceWithDefaultValue> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 7334161941683036846L;

		private EvaluationExpression inputKeyExtractor = EvaluationExpression.VALUE;

		private EvaluationExpression defaultExpression = FILTER_RECORDS;

		public void setInputKeyExtractor(EvaluationExpression inputKeyExtractor) {
			if (inputKeyExtractor == null)
				throw new NullPointerException("inputKeyExtractor must not be null");

			this.inputKeyExtractor = inputKeyExtractor;
		}

		public void setDefaultExpression(EvaluationExpression defaultExpression) {
			if (defaultExpression == null)
				throw new NullPointerException("defaultExpression must not be null");

			this.defaultExpression = defaultExpression;
		}

		public ReplaceWithDefaultValue withInputKeyExtractor(EvaluationExpression prop) {
			this.setInputKeyExtractor(prop);
			return this;
		}

		public ReplaceWithDefaultValue withDefaultExpression(EvaluationExpression prop) {
			this.setDefaultExpression(prop);
			return this;
		}

		public EvaluationExpression getDefaultExpression() {
			return this.defaultExpression;
		}

		public EvaluationExpression getInputKeyExtractor() {
			return this.inputKeyExtractor;
		}

		public static class Implementation extends
				SopremoCoGroup<Key, PactJsonObject, PactJsonObject, Key, PactJsonObject> {
			private EvaluationExpression inputKeyExtractor;

			private EvaluationExpression defaultExpression;

			@Override
			protected void coGroup(JsonNode key, StreamArrayNode values1, StreamArrayNode values2, JsonCollector out) {
				final Iterator<JsonNode> replaceValueIterator = values2.iterator();
				JsonNode replaceValue = replaceValueIterator.hasNext() ? replaceValueIterator.next() : null;

				final Iterator<JsonNode> valueIterator = values1.iterator();
				final EvaluationContext context = this.getContext();
				while (valueIterator.hasNext()) {
					JsonNode value = valueIterator.next();
					JsonNode replacement = replaceValue != null ? replaceValue :
						this.defaultExpression.evaluate(this.inputKeyExtractor.evaluate(value, context), context);
					out.collect(NullNode.getInstance(), this.inputKeyExtractor.set(value, replacement, context));
				}
			}
		}
	}

	@InputCardinality(min = 2, max = 2)
	public static class StrictReplace extends ElementaryOperator<StrictReplace> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 7334161941683036846L;

		private EvaluationExpression inputKeyExtractor;

		public void setInputKeyExtractor(EvaluationExpression inputKeyExtractor) {
			if (inputKeyExtractor == null)
				throw new NullPointerException("inputKeyExtractor must not be null");

			this.inputKeyExtractor = inputKeyExtractor;
		}

		public EvaluationExpression getInputKeyExtractor() {
			return this.inputKeyExtractor;
		}

		public StrictReplace withInputKeyExtractor(EvaluationExpression prop) {
			this.setInputKeyExtractor(prop);
			return this;
		}

		public static class Implementation extends
				SopremoMatch<Key, PactJsonObject, PactJsonObject, Key, PactJsonObject> {
			private EvaluationExpression inputKeyExtractor;

			@Override
			protected void match(final JsonNode key, final JsonNode value1, final JsonNode value2,
					final JsonCollector out) {
				out.collect(NullNode.getInstance(), this.inputKeyExtractor.set(value1, value2, this.getContext()));
			}
		}
	}
}