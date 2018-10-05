package hr.fer.zemris.java.hw05.db;

/**
 * Class that defines the conditional expression
 * 
 * @author Dinz
 *
 */
public class ConditionalExpression {
	/**
	 * Instance of the field getter that gets the field of the element to check in
	 * expression.
	 */
	private IFieldValueGetter fieldGetter;
	/**
	 * String literal that is used for comparison with the element.
	 */
	private String stringLiteral;
	/**
	 * Operator in expression.
	 */
	private IComparisonOperator ComparisonOperator;

	/**
	 * Constructs a new ConditionalExpression.
	 * 
	 * @param fieldGetter
	 *            Instance of the field getter.
	 * @param stringLiteral
	 *            String literal that is used for comparison.
	 * @param ComparisonOperator
	 *            Operator in expression.
	 */
	public ConditionalExpression(IFieldValueGetter fieldGetter, String stringLiteral,
			IComparisonOperator ComparisonOperator) {
		this.fieldGetter = fieldGetter;
		this.stringLiteral = stringLiteral;
		this.ComparisonOperator = ComparisonOperator;
	}

	/**
	 * Returns the field getter.
	 * 
	 * @return Field getter.
	 */
	public IFieldValueGetter getFieldGetter() {
		return fieldGetter;
	}

	/**
	 * Returns the string literal.
	 * 
	 * @return String literal.
	 */
	public String getStringLiteral() {
		return stringLiteral;
	}

	/**
	 * Returns the operator.
	 * 
	 * @return The operator.
	 */
	public IComparisonOperator getComparisonOperator() {
		return ComparisonOperator;
	}

}
