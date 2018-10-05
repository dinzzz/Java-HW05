package hr.fer.zemris.java.hw05.db;

import org.junit.Test;
import org.junit.Assert;

public class ConditionalExpressionTest {
	@Test
	public void lastNameTest() {
		ConditionalExpression expr = new ConditionalExpression(FieldValueGetters.LAST_NAME, "Pro*",
				ComparisonOperators.LIKE);

		StudentRecord record = new StudentRecord("0036493569", "Prosinečki", "Robert", 1);
		boolean actual = expr.getComparisonOperator().satisfied(expr.getFieldGetter().get(record), 
				expr.getStringLiteral());
		
		boolean expected = true;
		
		Assert.assertEquals(expected, actual);		
		
	}
	
	@Test
	public void lastNameFalseTest() {
		ConditionalExpression expr = new ConditionalExpression(FieldValueGetters.LAST_NAME, "Proz*",
				ComparisonOperators.LIKE);

		StudentRecord record = new StudentRecord("0036493569", "Prosinečki", "Robert", 1);
		boolean actual = expr.getComparisonOperator().satisfied(expr.getFieldGetter().get(record), 
				expr.getStringLiteral());
		
		boolean expected = false;
		
		Assert.assertEquals(expected, actual);		
		
	}
	
	@Test
	public void firstNameTest() {
		ConditionalExpression expr = new ConditionalExpression(FieldValueGetters.FIRST_NAME, "Ro*rt",
				ComparisonOperators.LIKE);

		StudentRecord record = new StudentRecord("0036493569", "Prosinečki", "Robert", 1);
		boolean actual = expr.getComparisonOperator().satisfied(expr.getFieldGetter().get(record), 
				expr.getStringLiteral());
		
		boolean expected = true;
		
		Assert.assertEquals(expected, actual);		
		
	}
	
	@Test
	public void firstNameFalseTest() {
		ConditionalExpression expr = new ConditionalExpression(FieldValueGetters.FIRST_NAME, "Ga*rt",
				ComparisonOperators.LIKE);

		StudentRecord record = new StudentRecord("0036493569", "Prosinečki", "Robert", 1);
		boolean actual = expr.getComparisonOperator().satisfied(expr.getFieldGetter().get(record), 
				expr.getStringLiteral());
		
		boolean expected = false;
		
		Assert.assertEquals(expected, actual);		
		
	}
	
	@Test
	public void jmbagTest() {
		ConditionalExpression expr = new ConditionalExpression(FieldValueGetters.JMBAG, "0036493569",
				ComparisonOperators.EQUALS);

		StudentRecord record = new StudentRecord("0036493569", "Prosinečki", "Robert", 1);
		boolean actual = expr.getComparisonOperator().satisfied(expr.getFieldGetter().get(record), 
				expr.getStringLiteral());
		
		boolean expected = true;
		
		Assert.assertEquals(expected, actual);		
		
	}
	
	@Test
	public void jmbagFalseTest() {
		ConditionalExpression expr = new ConditionalExpression(FieldValueGetters.JMBAG, "0026493569",
				ComparisonOperators.EQUALS);

		StudentRecord record = new StudentRecord("0036493569", "Prosinečki", "Robert", 1);
		boolean actual = expr.getComparisonOperator().satisfied(expr.getFieldGetter().get(record), 
				expr.getStringLiteral());
		
		boolean expected = false;
		
		Assert.assertEquals(expected, actual);		
		
	}

}
