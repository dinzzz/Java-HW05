package hr.fer.zemris.java.hw05.collections;

import java.util.Iterator;

/**
 * Class that demonstrates the use of SimpleHashtable class as shown in the
 * directions for the task. It prints out the elements in the table, cartesian
 * product of the table and proper exception that is printed on purpose.
 * 
 * @author Dinz
 *
 */
public class Primjer {
	/**
	 * Method that executes the class.
	 * 
	 * @param args
	 *            Arguments from the command line.
	 */
	public static void main(String[] args) {
		// create collection:
		SimpleHashtable<String, Integer> examMarks = new SimpleHashtable<>(2);
		// fill data:
		examMarks.put("Ivana", 2);
		examMarks.put("Ante", 2);
		examMarks.put("Jasna", 2);
		examMarks.put("Kristina", 5);
		examMarks.put("Ivana", 5); // overwrites old grade for Ivana
		examMarks.put("Dino", 3);
		examMarks.put("Dino", 4);
		examMarks.put("Dino", 5);
		examMarks.put("Ante", 1);
		for (SimpleHashtable.TableEntry<String, Integer> pair : examMarks) {
			System.out.printf("%s => %d%n", pair.getKey(), pair.getValue());
		}

		for (SimpleHashtable.TableEntry<String, Integer> pair1 : examMarks) {
			for (SimpleHashtable.TableEntry<String, Integer> pair2 : examMarks) {
				System.out.printf("(%s => %d) - (%s => %d)%n", pair1.getKey(), pair1.getValue(), pair2.getKey(),
						pair2.getValue());
			}
		}


		Iterator<SimpleHashtable.TableEntry<String, Integer>> iter = examMarks.iterator();
		while (iter.hasNext()) {
			SimpleHashtable.TableEntry<String, Integer> pair = iter.next();
			if (pair.getKey().equals("Ivana")) {
				iter.remove();
				iter.remove();
			}
		}

	}
}
