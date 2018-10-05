package hr.fer.zemris.java.hw05.db;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that demonstrates the use of the QueryFilter by testing it with the
 * prewritten data and the static expression. The class prints out the data in
 * the same way the main class StudentDB of the project should do.
 * 
 * @author Dinz
 *
 */
public class QueryFilterPrimjer {
	/**
	 * Main method of the class.
	 * 
	 * @param args
	 *            Arguments from the command line.
	 */
	public static void main(String[] args) {
		List<String> studenti = new ArrayList<>();

		studenti.add("0000000001	Akšamović	Marin	2");
		studenti.add("0000000003	Bosnić	Andrea	4");
		studenti.add("0000000004	Božić	Marin	5");
		studenti.add("0000000005	Brezović	Jusufadis	2");
		studenti.add("0000000006	Cvrlje	Ivan	3");
		studenti.add("0000000007	Čima	Sanjin	4");
		studenti.add("0000000009	Dean	Nataša	2");

		StudentDatabase db = new StudentDatabase(studenti);
		QueryParser parser = new QueryParser("query jmbag < \"0000000009\"");
		if (parser.isDirectQuery()) {
			StudentRecord r = db.forJMBAG(parser.getQueriedJMBAG());
			System.out.println("Direct query result name and surname: " + r.getFirstName() + " " + r.getLastName());
		} else {
			for (StudentRecord r : db.filter(new QueryFilter(parser.getQuery()))) {
				System.out
						.println("Multiple query result name and surname: " + r.getFirstName() + " " + r.getLastName());
			}
		}

	}

}
