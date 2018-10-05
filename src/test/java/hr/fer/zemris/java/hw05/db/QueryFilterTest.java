package hr.fer.zemris.java.hw05.db;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

public class QueryFilterTest {

	@Test
	public void acceptTest() {
		List<String> studenti = new ArrayList<>();

		studenti.add("0000000001	Akšamović	Marin	2");
		studenti.add("0000000003	Bosnić	Andrea	4");
		studenti.add("0000000004	Božić	Marin	5");
		studenti.add("0000000005	Brezović	Jusufadis	2");
		studenti.add("0000000006	Cvrlje	Ivan	3");
		studenti.add("0000000007	Čima	Sanjin	4");
		studenti.add("0000000009	Dean	Nataša	2");

		StudentDatabase db = new StudentDatabase(studenti);
		QueryParser parser = new QueryParser("query jmbag > \"0000000008\" and firstName LIKE \"N*\"");
		
		List<StudentRecord> rc = new ArrayList<>();

		for (StudentRecord r : db.filter(new QueryFilter(parser.getQuery()))) {
			rc.add(r);
		}
		
		String actual = "Nataša";
		String expected = rc.get(0).getFirstName();
		
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void declineTest() {
		List<String> studenti = new ArrayList<>();

		studenti.add("0000000001	Akšamović	Marin	2");
		studenti.add("0000000003	Bosnić	Andrea	4");
		studenti.add("0000000004	Božić	Marin	5");
		studenti.add("0000000005	Brezović	Jusufadis	2");
		studenti.add("0000000006	Cvrlje	Ivan	3");
		studenti.add("0000000007	Čima	Sanjin	4");
		studenti.add("0000000009	Dean	Nataša	2");

		StudentDatabase db = new StudentDatabase(studenti);
		QueryParser parser = new QueryParser("query jmbag > \"0000000008\" and firstName LIKE \"Z*\"");
		
		List<StudentRecord> rc = new ArrayList<>();

		for (StudentRecord r : db.filter(new QueryFilter(parser.getQuery()))) {
			rc.add(r);
		}
		
		boolean expected = true;
		boolean actual = rc.isEmpty();
		
		Assert.assertEquals(expected, actual);
	}
}
