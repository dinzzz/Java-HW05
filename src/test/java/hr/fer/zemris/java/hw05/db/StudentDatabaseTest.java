package hr.fer.zemris.java.hw05.db;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;

public class StudentDatabaseTest {
	
	StudentDatabase sd;
	
	@Before
	public void initialization() {
		List<String> students = new ArrayList<>();
		students.add("0000000001	Akšamović	Marin	2");
		students.add("	0000000002		Bakamović	Petra	3\r\n");
		students.add("0000000003	Bosnić	Andrea	4\r\n");
		students.add("0000000004	Božić	Marin	5");
		students.add("	0000000005	Brezović		Jusufadis	2");
		students.add("0000000006	Cvrlje	Ivan	3");
		students.add("0000000007	Čima Zbrljo	Sanjin	4");
		
		
		sd = new StudentDatabase(students);
		
		
	}
	
	@Test
	public void forJMBAGTest() {
		StudentRecord actual = sd.forJMBAG("0000000002");
		StudentRecord expected = new StudentRecord("0000000002", "Bakamović", "Petra", 3);
		
		StudentRecord actual1 = sd.forJMBAG("0000000002");
		StudentRecord expected1 = new StudentRecord("0000000002", "Bakamovićka", "Petrica", 4);
		
		StudentRecord actual2 = sd.forJMBAG("0000000006");
		StudentRecord expected2 = new StudentRecord("0000000006", "Cvrlje", "Ivan", 3);
		
		Assert.assertEquals(expected, actual);
		Assert.assertEquals(expected1, actual1);
		Assert.assertEquals(expected2, actual2);
	}
	
	@Test
	public void filterTrueTest() {
		List<StudentRecord> list = sd.filter(e -> true);
		
		int expected = 7;
		int actual = list.size();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test 
	public void filterFalseTest() {
		List<StudentRecord> list = sd.filter(e -> false);
		
		int expected = 0;
		int actual = list.size();
		
		Assert.assertEquals(expected, actual);
	}

}
