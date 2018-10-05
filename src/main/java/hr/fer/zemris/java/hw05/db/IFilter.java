package hr.fer.zemris.java.hw05.db;

/**
 * Interface whose implementations would filter the data according to the
 * requirements.
 * 
 * @author Dinz
 *
 */
public interface IFilter {
	/**
	 * Method that decides if the filter accepts the student record.
	 * 
	 * @param record
	 *            Record to be checked.
	 * @return True if the record is accepted, False otherwise.
	 */
	public boolean accepts(StudentRecord record);
}
