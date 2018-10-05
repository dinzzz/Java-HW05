package hr.fer.zemris.java.hw05.db;

import org.junit.Test;
import org.junit.Assert;

public class FieldValueGettersTest {

	@Test
	public void getFirstNameTest() {
		StudentRecord record = new StudentRecord("0036493569", "Božić", "Dino", 5);
		StudentRecord record2 = new StudentRecord("0036493578", "Robson-Kanu", "Hal", 4);
		StudentRecord record3 = new StudentRecord("0036111111", "de Lima", "Luiz Nazario", 2);
		
		String expected = "Dino";
		String actual = FieldValueGetters.FIRST_NAME.get(record);
		
		String expected2 = "Hal";
		String actual2 = FieldValueGetters.FIRST_NAME.get(record2);
		
		String expected3 = "Luiz Nazario";
		String actual3 = FieldValueGetters.FIRST_NAME.get(record3);
		
		Assert.assertEquals(expected, actual);
		Assert.assertEquals(expected2, actual2);
		Assert.assertEquals(expected3, actual3);
	}
	
	@Test
	public void getLastNameTest() {
		StudentRecord record = new StudentRecord("0036493569", "Božić", "Dino", 5);
		StudentRecord record2 = new StudentRecord("0036493578", "Robson-Kanu", "Hal", 4);
		StudentRecord record3 = new StudentRecord("0036111111", "de Lima", "Luiz Nazario", 2);
		
		String expected = "Božić";
		String actual = FieldValueGetters.LAST_NAME.get(record);
		
		String expected2 = "Robson-Kanu";
		String actual2 = FieldValueGetters.LAST_NAME.get(record2);
		
		String expected3 = "de Lima";
		String actual3 = FieldValueGetters.LAST_NAME.get(record3);
		
		Assert.assertEquals(expected, actual);
		Assert.assertEquals(expected2, actual2);
		Assert.assertEquals(expected3, actual3);
	}
	
	@Test
	public void getJMBAGTest() {
		StudentRecord record = new StudentRecord("0036493569", "Božić", "Dino", 5);
		StudentRecord record2 = new StudentRecord("0036493578", "Robson-Kanu", "Hal", 4);
		StudentRecord record3 = new StudentRecord("0036111111", "de Lima", "Luiz Nazario", 2);
		
		String expected = "0036493569";
		String actual = FieldValueGetters.JMBAG.get(record);
		
		String expected2 = "0036493578";
		String actual2 = FieldValueGetters.JMBAG.get(record2);
		
		String expected3 = "0036111111";
		String actual3 = FieldValueGetters.JMBAG.get(record3);
		
		Assert.assertEquals(expected, actual);
		Assert.assertEquals(expected2, actual2);
		Assert.assertEquals(expected3, actual3);
	}
}
