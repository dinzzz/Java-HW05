package hr.fer.zemris.java.hw05.db;

/**
 * Class that implements the IComparisonOperator strategy and define different
 * types of operators.
 * 
 * @author Dinz
 *
 */
public class ComparisonOperators {
	/**
	 * Integer representation of the "less then" operator.
	 */
	public static final int LESS_INT = -1;
	/**
	 * Integer representation of the "greater then" operator.
	 */
	public static final int GREATER_INT = 1;
	/**
	 * Integer representation of the "equal to" operator.
	 */
	public static final int EQUAL_INT = 0;

	/**
	 * Defines the "less then" operation.
	 */
	public static final IComparisonOperator LESS = (e, f) -> {
		return (e.compareTo(f) <= LESS_INT);

	};
	/**
	 * Defines the "less then or equal to" operator.
	 */
	public static final IComparisonOperator LESS_OR_EQUALS = (e, f) -> {
		return (e.compareTo(f) <= EQUAL_INT);

	};
	/**
	 * Defines the "greater then" operator.
	 */
	public static final IComparisonOperator GREATER = (e, f) -> {
		return (e.compareTo(f) >= GREATER_INT);
	};
	/**
	 * Defines the "greater then or equal to" operator.
	 */
	public static final IComparisonOperator GREATER_OR_EQUALS = (e, f) -> {
		return (e.compareTo(f) >= EQUAL_INT);

	};
	/**
	 * Defines the "equal to" operator.
	 */
	public static final IComparisonOperator EQUALS = (e, f) -> {
		return (e.compareTo(f) == EQUAL_INT);

	};
	/**
	 * Defines the "not equal to" operator.
	 */
	public static final IComparisonOperator NOT_EQUALS = (e, f) -> {
		return (e.compareTo(f) != EQUAL_INT);

	};
	/**
	 * Defines the "LIKE" operator which checks the matching of the string with the
	 * string litteral.
	 * 
	 * @throws IllegalArgumentException
	 *             If the wildcars are used wrongly.
	 */
	public static final IComparisonOperator LIKE = (e, f) -> {
		if (f.contains("*")) {
			int index = f.indexOf("*");
			boolean multiple = index != f.lastIndexOf("*");

			if (multiple) {
				throw new IllegalArgumentException("Invalid use of wildcards.");
			}

			if (f.startsWith("*")) {
				String checker = f.replace("*", "");
				return (e.endsWith(checker));
			}

			else if (f.endsWith("*")) {
				String checker = f.replace("*", "");
				return (e.startsWith(checker));
			} else {
				String startChecker = f.split("\\*")[0];
				String endChecker = f.split("\\*")[1];
				return (e.startsWith(startChecker) && e.endsWith(endChecker)
						&& e.length() >= (startChecker.length() + endChecker.length()));
			}
		} else {
			return e.equals(f);
		}
	};
}
