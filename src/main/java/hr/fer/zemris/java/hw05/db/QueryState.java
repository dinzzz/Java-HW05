package hr.fer.zemris.java.hw05.db;

/**
 * Enumeration that defines the state of the QueryLexer.
 * 
 * @author Dinz
 *
 */
public enum QueryState {
	/**
	 * Defines that the lexer run into direct query.
	 */
	DIRECT_QUERY,
	/**
	 * Basic state of the lexer, used for any other query besides the direct query.
	 */
	BASIC_STATE;
}
