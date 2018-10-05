package hr.fer.zemris.java.hw05.db;

/**
 * Interface that defines the strategy for getting field values from the student
 * record.
 * 
 * @author Dinz
 *
 */
public interface IFieldValueGetter {
	/**
	 * Returns the field value from the given record.
	 * 
	 * @param record
	 *            Record whose field we have to get.
	 * @return Field value from the given record.
	 */
	public String get(StudentRecord record);

}
