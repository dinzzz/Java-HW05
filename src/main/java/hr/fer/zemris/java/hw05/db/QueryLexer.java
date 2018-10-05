package hr.fer.zemris.java.hw05.db;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;

/**
 * Class that represents a use of the QueryLexer which provides a lexical
 * analysis of the given query input. The class forms the list of expressions
 * based on the query input and forward them to the parser.
 * 
 * @author Dinz
 *
 */
public class QueryLexer {
	/**
	 * User input.
	 */
	private String expression;
	/**
	 * State of the QueryLexer.
	 */
	private QueryState state = QueryState.BASIC_STATE;
	/**
	 * List of expressions to forward.
	 */
	List<ConditionalExpression> expressions = new ArrayList<>();
	/**
	 * Flag that determines if the input is a single expression.
	 */
	private boolean singleFlag = false;

	/**
	 * Constructs a new QueryLexer based on the user input without the keyword
	 * "query".
	 * 
	 * @param expression
	 *            User input.
	 */
	public QueryLexer(String expression) {
		this.expression = expression;
		this.parse();
	}

	/**
	 * Method that parses the expression. It decides if the input is multiple
	 * expression delimited by the keyword "and" or the single expression. After the
	 * decision it runs the analysis.
	 */
	private void parse() {
		if (containsIgnoreCase(expression, " and ")) {
			parseMultipleExpression(expression);
		} else {
			singleFlag = true;
			parseSingleExpression(expression);
		}
	}

	/**
	 * Method that analyzes multiple expressions.
	 * 
	 * @param expression
	 *            User input.
	 */
	private void parseMultipleExpression(String expression) {
		String[] split = expression.split("(?i)and");
		for (String s : split) {
			parseSingleExpression(s);
		}
	}

	/**
	 * Method that analyzes a single expression.
	 * 
	 * @param expression
	 *            User input
	 * @throws IllegalArgumentException
	 *             If the expression is input wrongly.
	 */
	private void parseSingleExpression(String expression) {
		String checker = expression;
		if (checker.toUpperCase().endsWith("AND")) {
			throw new IllegalArgumentException("There is nothing behind \"and\"");
		}
		if (expression.contains(">=")) {
			String[] elements = develop(expression, ">=");
			IFieldValueGetter attribute = attribute(elements[0]);
			String litteral = elements[1].replace("\"", "");
			IComparisonOperator operator = ComparisonOperators.GREATER_OR_EQUALS;

			expressions.add(new ConditionalExpression(attribute, litteral, operator));

		} else if (expression.contains("<=")) {
			String[] elements = develop(expression, "<=");
			IFieldValueGetter attribute = attribute(elements[0]);
			String litteral = elements[1].replace("\"", "");
			IComparisonOperator operator = ComparisonOperators.LESS_OR_EQUALS;

			expressions.add(new ConditionalExpression(attribute, litteral, operator));
		} else if (expression.contains("!=")) {
			String[] elements = develop(expression, "!=");
			IFieldValueGetter attribute = attribute(elements[0]);
			String litteral = elements[1].replace("\"", "");
			IComparisonOperator operator = ComparisonOperators.NOT_EQUALS;

			expressions.add(new ConditionalExpression(attribute, litteral, operator));
		}

		else if (expression.contains("<")) {
			String[] elements = develop(expression, "<");
			IFieldValueGetter attribute = attribute(elements[0]);
			String litteral = elements[1].replace("\"", "");
			IComparisonOperator operator = ComparisonOperators.LESS;

			expressions.add(new ConditionalExpression(attribute, litteral, operator));
		}

		else if (expression.contains(">")) {
			String[] elements = develop(expression, ">");
			IFieldValueGetter attribute = attribute(elements[0]);
			String litteral = elements[1].replace("\"", "");
			IComparisonOperator operator = ComparisonOperators.GREATER;

			expressions.add(new ConditionalExpression(attribute, litteral, operator));
		} else if (expression.contains("=")) {
			String[] elements = develop(expression, "=");
			IFieldValueGetter attribute = attribute(elements[0]);
			String litteral = elements[1].replace("\"", "");
			IComparisonOperator operator = ComparisonOperators.EQUALS;

			if (singleFlag && attribute == FieldValueGetters.JMBAG) {
				this.setState(QueryState.DIRECT_QUERY);
			}

			expressions.add(new ConditionalExpression(attribute, litteral, operator));
		} else if (expression.contains("LIKE")) {
			String[] elements = develop(expression, "LIKE");
			IFieldValueGetter attribute = attribute(elements[0]);
			String litteral = elements[1].replace("\"", "");
			IComparisonOperator operator = ComparisonOperators.LIKE;

			expressions.add(new ConditionalExpression(attribute, litteral, operator));
		} else {
			throw new IllegalArgumentException("Wrong query.");
		}
	}

	/**
	 * Gets the state of the QueryLexer.
	 * 
	 * @return State of the QueryLexer.
	 */
	public QueryState getState() {
		return this.state;
	}

	/**
	 * Sets the state of the QueryLexer.
	 * 
	 * @param state
	 *            State to be set.
	 */
	public void setState(QueryState state) {
		this.state = state;
	}

	/**
	 * Gets the list of stored expressions.
	 * 
	 * @return List of the conditional expressions.
	 */
	public List<ConditionalExpression> getExpressions() {
		return this.expressions;
	}

	/**
	 * Method that develops the expressions into certain parts by spliting them with
	 * an operator. The parts are the attribute of the expression and the string
	 * literal.
	 * 
	 * @param string
	 *            String to be developed.
	 * @param delimiter
	 *            Delimiter for the split.
	 * @return Attribute of the expression and string literal stored in a string
	 *         array.
	 * @throws IllegalArgumentException
	 *             If the expression is invalid.
	 *
	 */
	private String[] develop(String string, String delimiter) {
		String[] split = string.split(delimiter);
		if (split.length != 2) {
			throw new IllegalArgumentException("Wrong expression in the query.");
		}
		for (int i = 0; i < split.length; i++) {
			split[i] = split[i].trim();
		}

		return split;
	}

	/**
	 * Method that defines the attribute of the expression.
	 * 
	 * @param string
	 *            String to be checked.
	 * @return Value getter of the field according to the input string.
	 * @throws IllegalArgumentException
	 *             If the attribute is invalid.
	 */
	private IFieldValueGetter attribute(String string) {
		if (string.equals("firstName")) {
			return FieldValueGetters.FIRST_NAME;
		} else if (string.equals("lastName")) {
			return FieldValueGetters.LAST_NAME;
		} else if (string.equals("jmbag")) {
			return FieldValueGetters.JMBAG;
		} else {
			throw new IllegalArgumentException("Wrong attribute in the query.");
		}
	}

	/**
	 * Method that checks if the string is contained in another string without being
	 * case sensitive.
	 * 
	 * @param str
	 *            String element.
	 * @param searchStr
	 *            String to be checked.
	 * @return True if the string is contained, False otherwise.
	 */
	public static boolean containsIgnoreCase(String str, String searchStr) {
		if (str == null || searchStr == null)
			return false;

		final int length = searchStr.length();
		if (length == 0)
			return true;

		for (int i = str.length() - length; i >= 0; i--) {
			if (str.regionMatches(true, i, searchStr, 0, length))
				return true;
		}
		return false;
	}
}
