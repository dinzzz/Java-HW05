package hr.fer.zemris.java.hw05.db;

/**
 * Class that demonstrates the use of the ConditionalExpression.
 * 
 * @author Dinz
 *
 */
public class Primjer {
	/**
	 * Method that executes the class.
	 * 
	 * @param args
	 *            Arguments from the command line.
	 */
	public static void main(String[] args) {
		ConditionalExpression expr = new ConditionalExpression(FieldValueGetters.LAST_NAME, "Pro*",
				ComparisonOperators.LIKE);
		StudentRecord record = new StudentRecord("0036493569", "Prosinečki", "Robert", 1);
		boolean recordSatisfies = expr.getComparisonOperator().satisfied(expr.getFieldGetter().get(record), 
				expr.getStringLiteral() // returns "Bos*"
		);
		System.out.println(recordSatisfies); // true

	}

}
