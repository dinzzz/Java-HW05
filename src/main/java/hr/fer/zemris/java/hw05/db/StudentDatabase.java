package hr.fer.zemris.java.hw05.db;

import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.hw05.collections.SimpleHashtable;

/**
 * Class that represents the student database. Database is created from the list
 * of strings for each record and then developed into one list format and one
 * Hashtable format. Database's further use is filtering the students based on
 * the given filter directions.
 * 
 * @author Dinz
 *
 */
public class StudentDatabase {
	/**
	 * ArrayList that stores records based on the order input.
	 */
	private List<StudentRecord> records = new ArrayList<>();
	/**
	 * Hashtable that stores records based on the hashcode.
	 */
	private SimpleHashtable<String, StudentRecord> index = new SimpleHashtable<>();

	/**
	 * Constructs a new database from the list of students by putting them into the
	 * arraylist and the hashtable.
	 * 
	 * @param students
	 *            List of students in string format.
	 */
	public StudentDatabase(List<String> students) {

		for (String student : students) {
			String[] split = student.trim().split("\t+");
			String jmbag = split[0];
			String lastName = split[1];
			String firstName = split[2];
			int finalGrade = Integer.parseInt(split[3]);

			StudentRecord studentR = new StudentRecord(jmbag, lastName, firstName, finalGrade);
			records.add(studentR);
			index.put(jmbag, studentR);

		}
	}

	/**
	 * Gets the student record for the given jmbag.
	 * 
	 * @param jmbag
	 *            Jmbag of the student record.
	 * @return Student record.
	 */
	public StudentRecord forJMBAG(String jmbag) {
		return index.get(jmbag);
	}

	/**
	 * Method that filters the student database according to the expression given by
	 * the filter instance.
	 * 
	 * @param filter
	 *            Instance of the filter.
	 * @return Filtered list of the student records.
	 */
	public List<StudentRecord> filter(IFilter filter) {
		List<StudentRecord> temp = new ArrayList<>();
		for (StudentRecord student : records) {
			if (filter.accepts(student)) {
				temp.add(student);
			}
		}
		return temp;
	}

}
