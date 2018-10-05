package hr.fer.zemris.java.hw05.db;

/**
 * Class that demonstrates the use of the QueryParser. It prints the adequate
 * data and the exception if the correspondent line is uncommented.
 * 
 * @author Dinz
 *
 */
public class QueryParserPrimjer {
	/**
	 * Method that executes the class.
	 * 
	 * @param args
	 *            Arguments from the command line.
	 */
	public static void main(String[] args) {
		QueryParser qp1 = new QueryParser("query jmbag =\"0123456789\" ");
		System.out.println("isDirectQuery(): " + qp1.isDirectQuery()); // true
		System.out.println("jmbag was: " + qp1.getQueriedJMBAG()); // 0123456789
		System.out.println("size: " + qp1.getQuery().size()); // 1

		QueryParser qp2 = new QueryParser("query jmbag=\"0123456789\" and lastName>\"J\"");
		System.out.println("isDirectQuery(): " + qp2.isDirectQuery()); // false
		// System.out.println(qp2.getQueriedJMBAG()); // would throw!
		System.out.println("size: " + qp2.getQuery().size()); // 2

	}

}
