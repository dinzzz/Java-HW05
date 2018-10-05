package hr.fer.zemris.java.hw05.db;

/**
 * Class that implements IFieldValueGetter strategy with different type of
 * fields defining student records.
 * 
 * @author Dinz
 *
 */
public class FieldValueGetters {
	/**
	 * Getter for the first name of the student.
	 */
	public static final IFieldValueGetter FIRST_NAME = e -> {
		return e.getFirstName();
	};
	/**
	 * Getter for the last name of the student.
	 */
	public static final IFieldValueGetter LAST_NAME = e -> {
		return e.getLastName();
	};
	/**
	 * Getter for the JMBAG of the student.
	 */
	public static final IFieldValueGetter JMBAG = e -> {
		return e.getJmbag();
	};

}
