package hr.fer.zemris.java.hw05.db;

import org.junit.Test;
import org.junit.Assert;

public class ComparisonOperatorsTest {
	//testiraj exception
	
	@Test
	public void lessTest() {
		String tester = "Split";
		String checker = "Zagreb";
		
		IComparisonOperator oper = ComparisonOperators.LESS;
		
		boolean expected = true;
		boolean actual = oper.satisfied(tester, checker);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void lessFalseTest() {
		String checker = "Split";
		String tester = "Zagreb";
		
		IComparisonOperator oper = ComparisonOperators.LESS;
		
		boolean expected = false;
		boolean actual = oper.satisfied(tester, checker);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void lessOrEqualTest() {
		String checker = "Zagreb";
		String tester = "Zagreb";
		
		IComparisonOperator oper = ComparisonOperators.LESS_OR_EQUALS;
		
		boolean expected = true;
		boolean actual = oper.satisfied(tester, checker);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void lessOrEqualFalseTest() {
		String checker = "Split";
		String tester = "Zagreb";
		
		IComparisonOperator oper = ComparisonOperators.LESS_OR_EQUALS;
		
		boolean expected = false;
		boolean actual = oper.satisfied(tester, checker);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void greaterTest() {
		String checker = "Split";
		String tester = "Zagreb";
		
		IComparisonOperator oper = ComparisonOperators.GREATER;
		
		boolean expected = true;
		boolean actual = oper.satisfied(tester, checker);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void greaterFalseTest() {
		String checker = "Zagreb";
		String tester = "Split";
		
		IComparisonOperator oper = ComparisonOperators.GREATER;
		
		boolean expected = false;
		boolean actual = oper.satisfied(tester, checker);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void greaterOrEqualTest() {
		String checker = "Zagreb";
		String tester = "Zagreb";
		
		IComparisonOperator oper = ComparisonOperators.GREATER_OR_EQUALS;
		
		boolean expected = true;
		boolean actual = oper.satisfied(tester, checker);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void greaterOrEqualFalseTest() {
		String checker = "Zagreb";
		String tester = "Split";
		
		IComparisonOperator oper = ComparisonOperators.GREATER_OR_EQUALS;
		
		boolean expected = false;
		boolean actual = oper.satisfied(tester, checker);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void equalTest() {
		String checker = "Zagreb";
		String tester = "Zagreb";
		
		IComparisonOperator oper = ComparisonOperators.EQUALS;
		
		boolean expected = true;
		boolean actual = oper.satisfied(tester, checker);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test 
	public void equalFalseTest() {
		String checker = "Zagreb";
		String tester = "Zagrebčanec";
		
		IComparisonOperator oper = ComparisonOperators.EQUALS;
		
		boolean expected = false;
		boolean actual = oper.satisfied(tester, checker);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void notEqualTest() {
		String checker = "Zagreb";
		String tester = "Zagrebčanec";
		
		IComparisonOperator oper = ComparisonOperators.NOT_EQUALS;
		
		boolean expected = true;
		boolean actual = oper.satisfied(tester, checker);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void notEqualFalseTest() {
		String checker = "Zagreb";
		String tester = "Zagreb";
		
		IComparisonOperator oper = ComparisonOperators.NOT_EQUALS;
		
		boolean expected = false;
		boolean actual = oper.satisfied(tester, checker);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void likeTest() {
		String checker = "Za*";
		String tester = "Zagreb";
		
		IComparisonOperator oper = ComparisonOperators.LIKE;
		
		boolean expected = true;
		boolean actual = oper.satisfied(tester, checker);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void likeFalseTest() {
		String checker = "grz";
		String tester = "Zagreb";
		
		IComparisonOperator oper = ComparisonOperators.LIKE;
		
		boolean expected = false;
		boolean actual = oper.satisfied(tester, checker);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void likeWildcardStartTest() {
		String checker = "*greb";
		String tester = "Zagreb";
		
		IComparisonOperator oper = ComparisonOperators.LIKE;
		
		boolean expected = true;
		boolean actual = oper.satisfied(tester, checker);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void likeWildcardMiddleTest() {
		String checker = "Za*eb";
		String tester = "Zagreb";
		
		IComparisonOperator oper = ComparisonOperators.LIKE;
		
		boolean expected = true;
		boolean actual = oper.satisfied(tester, checker);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void likeWildcardEndTest() {
		String checker = "*eb";
		String tester = "Zagreb";
		
		IComparisonOperator oper = ComparisonOperators.LIKE;
		
		boolean expected = true;
		boolean actual = oper.satisfied(tester, checker);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void likeWildcardStartFalseTest() {
		String checker = "*areb";
		String tester = "Zagreb";
		
		IComparisonOperator oper = ComparisonOperators.LIKE;
		
		boolean expected = false;
		boolean actual = oper.satisfied(tester, checker);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void likeWildcardMiddleFalseTest() {
		String checker = "Zat*eb";
		String tester = "Zagreb";
		
		IComparisonOperator oper = ComparisonOperators.LIKE;
		
		boolean expected = false;
		boolean actual = oper.satisfied(tester, checker);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void likeWildcardEndFalseTest() {
		String checker = "Zb*";
		String tester = "Zagreb";
		
		IComparisonOperator oper = ComparisonOperators.LIKE;
		
		boolean expected = false;
		boolean actual = oper.satisfied(tester, checker);
		
		Assert.assertEquals(expected, actual);
	}

}
