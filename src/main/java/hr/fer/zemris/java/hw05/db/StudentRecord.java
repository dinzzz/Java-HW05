package hr.fer.zemris.java.hw05.db;

/**
 * Class that represents a student record in a database. Student record is
 * defined by its jmbag, last name, first name and final grade.
 * 
 * @author Dinz
 *
 */
public class StudentRecord {
	/**
	 * JMBAG of the student.
	 */
	private String jmbag;
	/**
	 * Last name of the student.
	 */
	private String lastName;
	/**
	 * First name of the student.
	 */
	private String firstName;
	/**
	 * Final grade of the student.
	 */
	private int finalGrade;

	/**
	 * Constructs a new student record from the given data.
	 * 
	 * @param jmbag
	 *            JMBAG of the student.
	 * @param lastName
	 *            Last name of the student.
	 * @param firstName
	 *            First name of the student.
	 * @param finalGrade
	 *            Final grade of the student
	 */
	public StudentRecord(String jmbag, String lastName, String firstName, int finalGrade) {
		this.jmbag = jmbag;
		this.lastName = lastName;
		this.firstName = firstName;
		this.finalGrade = finalGrade;
	}

	/**
	 * Gets the final grade.
	 * 
	 * @return Final grade.
	 */
	public int getFinalGrade() {
		return finalGrade;
	}

	/**
	 * Sets the final grade.
	 * 
	 * @param finalGrade
	 *            Final grade.
	 */
	public void setFinalGrade(int finalGrade) {
		this.finalGrade = finalGrade;
	}

	/**
	 * Returns the JMBAG of the student.
	 * 
	 * @return JMBAG of the student.
	 */
	public String getJmbag() {
		return jmbag;
	}

	/**
	 * Returns the last name of the student.
	 * 
	 * @return Last name of the student.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Returns the first name of the student.
	 * 
	 * @return First name of the student.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Calculates the hashcode of the student record based on the unique jmbag.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jmbag == null) ? 0 : jmbag.hashCode());
		return result;
	}

	/**
	 * Method that checks if the two records are the same.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentRecord other = (StudentRecord) obj;
		if (jmbag == null) {
			if (other.jmbag != null)
				return false;
		} else if (!jmbag.equals(other.jmbag))
			return false;
		return true;
	}

}
