package hr.fer.zemris.java.hw05.db;

/**
 * Defines the interface which represent comparison operators.
 * 
 * @author Dinz
 *
 */
public interface IComparisonOperator {
	/**
	 * Method that checks if the string is satisfactory based on the operator and
	 * the expression.
	 * 
	 * @param value1
	 *            String to be checked.
	 * @param value2
	 *            Expression that has to be satisfied.
	 * @return True if the string is satisfactory, False otherwise.
	 */
	public boolean satisfied(String value1, String value2);
}
