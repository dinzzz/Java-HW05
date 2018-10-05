package hr.fer.zemris.java.hw05.db;

import java.util.List;

/**
 * Class that implements the filter for the student records based on the
 * expressions given by the query.
 * 
 * @author Dinz
 *
 */
public class QueryFilter implements IFilter {
	/**
	 * List of expressions to check data.
	 */
	private List<ConditionalExpression> expressions;

	/**
	 * Constructs a new filter based on the expressions.
	 * 
	 * @param expressions
	 */
	public QueryFilter(List<ConditionalExpression> expressions) {
		this.expressions = expressions;
	}

	/**
	 * Method that determines is the student record accepted based on the
	 * expressions.
	 */
	@Override
	public boolean accepts(StudentRecord record) {
		for (ConditionalExpression expr : expressions) {

			boolean recordSatisfies = expr.getComparisonOperator().satisfied(expr.getFieldGetter().get(record),
					expr.getStringLiteral());

			if (!recordSatisfies) {
				return false;
			}
		}

		return true;
	}

}
