package hr.fer.zemris.java.hw05.collections;

import org.junit.Test;
import org.junit.Assert;

public class SimpleHashTableTest {
	
	@Test
	public void singlePutTest() {
		SimpleHashtable<String, Integer> table = new SimpleHashtable<>();
		table.put("Dino", 11);
		
		int expected = 1;
		int actual = table.size();
		
		int expected2 = 11;
		int actual2 = table.get("Dino");
		
		Assert.assertEquals(expected, actual);
		Assert.assertEquals(expected2, actual2);
	}
	
	@Test
	public void multiplePutTest() {
		SimpleHashtable<String, Integer> table = new SimpleHashtable<>();
		table.put("Dino", 11);
		table.put("Domagoj", 12);
		table.put("Kino", 13);
		table.put("Dinzob", 14);
		table.put("MadMax", 15);
		table.put("DajGa", 16);
		table.put("SamoRomantika", 17);
		table.put("NocniHakl", 18);
		table.put("CocnaNoZnjicaVo", 19);
		table.put("Rene", 20);
		table.put("Tarik", 21);
		table.put("Spektakluk", 22);
		table.put("Dino", 66);
		
		
		int expected = 12;
		int actual = table.size();
		
		
		int expected2 = 66;
		int actual2 = table.get("Dino");
		
		
		
		Assert.assertEquals(expected, actual);
		Assert.assertEquals(expected2, actual2);
		
	}
	
	
	@Test
	public void containsKeyTest() {
		SimpleHashtable<String, Integer> table = new SimpleHashtable<>();
		table.put("Dino", 11);
		
		boolean expected = true;
		boolean actual = table.containsKey("Dino");
		
		boolean expected2 = false;
		boolean actual2 = table.containsKey("DinoB");
		
		Assert.assertEquals(expected, actual);
		Assert.assertEquals(expected2, actual2);
	}
	
	@Test
	public void containsValueTest() {
		SimpleHashtable<String, Integer> table = new SimpleHashtable<>();
		table.put("Dino", 11);
		
		boolean expected = true;
		boolean actual = table.containsValue(11);
		
		boolean expected2 = false;
		boolean actual2 = table.containsValue(16);
		
		Assert.assertEquals(expected, actual);
		Assert.assertEquals(expected2, actual2);
	}
	
	@Test
	public void removeTest() {
		SimpleHashtable<String, Integer> table = new SimpleHashtable<>();
		table.put("Dino", 11);
		table.put("Domagoj", 12);
		table.put("Kino", 13);
		table.put("Dinzob", 14);
		table.put("MadMax", 15);
		table.put("DajGa", 16);
		table.put("SamoRomantika", 17);
		table.put("NocniHakl", 18);
		table.put("CocnaNoZnjicaVo", 19);
		table.put("Rene", 20);
		table.put("Tarik", 21);
		table.put("Spektakluk", 22);
		table.put("Dino", 66);
		
		table.remove("Dino");
		
		int expected = 11;
		int actual = table.size();
		
		Assert.assertEquals(expected, actual);
		
		table.remove("Tarik");
			
		
		int expected2 = 10;
		int actual2 = table.size();
		
		Assert.assertEquals(expected2, actual2);
		
		boolean expected3 = false;
		boolean actual3 = table.containsKey("Tarik");	
		
		Assert.assertEquals(expected3, actual3);
		
		
	}
	
	
	
	@Test
	public void clearTest() {
		SimpleHashtable<String, Integer> table = new SimpleHashtable<>();
		table.put("Dino", 11);
		table.put("Domagoj", 12);
		table.put("Kino", 13);
		table.put("Dinzob", 14);
		table.put("MadMax", 15);
		table.put("DajGa", 16);
		table.put("SamoRomantika", 17);
		table.put("NocniHakl", 18);
		table.put("CocnaNoZnjicaVo", 19);
		table.put("Rene", 20);
		table.put("Tarik", 21);
		table.put("Spektakluk", 22);
		table.put("Dino", 66);
		
		boolean expected1 = false;
		boolean actual1 = table.isEmpty();
		
		Assert.assertEquals(expected1, actual1);
		
		table.clear();
		
		boolean expected = true;
		boolean actual = table.isEmpty();
		
		Assert.assertEquals(expected, actual);
	}

}
