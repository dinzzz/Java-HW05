package hr.fer.zemris.java.hw05.db;

import java.util.List;

/**
 * Class that parses the query input. Based on that input, the parser creates
 * the list of expression needed to be processed on data. Also defines if the
 * query is direct query or not, and the database behaves according to the
 * result.
 * 
 * @author Dinz
 *
 */
public class QueryParser {
	/**
	 * Text of the query.
	 */
	private String queryString;
	/**
	 * Lexer used for parsing the query.
	 */
	private QueryLexer lexer;

	/**
	 * Constructs a new parser by removing the "query" keyword from the input.
	 * 
	 * @param input
	 *            Text input from the user.
	 * @throws IllegalArgumentException
	 *             if the query is input wrongly.
	 */
	public QueryParser(String input) {
		if (!input.trim().startsWith("query")) {
			throw new IllegalArgumentException("Query must be the first keyword in the input.");
		}
		if (input.trim().equals("query")) {
			throw new IllegalArgumentException("There is only query keyword inputted!");
		}
		queryString = input.split("query")[1].trim();
		lexer = new QueryLexer(queryString);
	}

	/**
	 * Checks if the query is direct.
	 * 
	 * @return True if the query is direct, False otherwise.
	 */
	public boolean isDirectQuery() {
		return (lexer.getState() == QueryState.DIRECT_QUERY);
	}

	/**
	 * Method that returns the queried JMBAG if the query is direct.
	 * 
	 * @return Queried JMBAG if the query is direct.
	 * @throws IllegalStateException
	 *             If the query is not direct.
	 */
	public String getQueriedJMBAG() {
		if (isDirectQuery()) {
			return this.getQuery().get(0).getStringLiteral();
		} else {
			throw new IllegalStateException("Query is not direct.");
		}
	}

	/**
	 * Returns the query from the input in shape of the list of expression to be
	 * used to process the data.
	 * 
	 * @return List of conditional expressions.
	 */
	public List<ConditionalExpression> getQuery() {
		return lexer.getExpressions();
	}
}
