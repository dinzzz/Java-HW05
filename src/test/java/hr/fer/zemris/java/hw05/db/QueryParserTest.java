package hr.fer.zemris.java.hw05.db;

import org.junit.Test;
import org.junit.Assert;

public class QueryParserTest {

	@Test
	public void directQueryTest() {
		QueryParser parser = new QueryParser("query jmbag = \"0000000009\"");

		boolean expected = true;
		boolean actual = parser.isDirectQuery();
		
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void directQueryFalseTest() {
		QueryParser parser = new QueryParser("query jmbag < \"0000000009\"");

		boolean expected = false;
		boolean actual = parser.isDirectQuery();
		
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void getQueriedTest() {
		
		QueryParser parser = new QueryParser("query jmbag = \"0000000009\"");

		String expected = "0000000009";
		String actual = parser.getQueriedJMBAG();
		
		Assert.assertEquals(expected, actual);
	}

	

}
