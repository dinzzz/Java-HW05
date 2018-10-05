package hr.fer.zemris.java.hw05.db;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class that executes the filtering of the student database based on the query
 * input by the user. The program is running until the users enters "exit" into
 * the console. After the wanted query the program will filter the students and
 * print out the correspondent ones in an adequate output.
 * 
 * @author Dinz
 *
 */
public class StudentDB {
	/**
	 * Main method that executes the class.
	 * 
	 * @param args
	 *            Arguments from the command line.
	 * @throws IOException
	 *             If there is no such file in the current directory.
	 */
	public static void main(String[] args) throws IOException {

		List<String> lines = Files.readAllLines(Paths.get("./database.txt"), StandardCharsets.UTF_8);
		StudentDatabase db = new StudentDatabase(lines);
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.print("> ");
			String input = sc.nextLine();

			if (input.trim().equals("exit")) {
				break;
			}
			try {
				QueryParser parser = new QueryParser(input);

				if (parser.isDirectQuery()) {
					StudentRecord r = db.forJMBAG(parser.getQueriedJMBAG());
					List<StudentRecord> rc = new ArrayList<>();
					rc.add(r);
					formatAndPrint(rc, r.getLastName().length(), r.getFirstName().length());
					System.out.println("Records selected: " + rc.size());
				} else {
					List<StudentRecord> records = db.filter(new QueryFilter(parser.getQuery()));
					if (!records.isEmpty()) {
						formatAndPrint(records, longestSurname(records), longestName(records));
					}
					System.out.println("Records selected: " + records.size());

				}

			} catch (IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
			} catch (NullPointerException ex) {
				System.out.println("Records selected: 0");
			}

		}

		sc.close();

	}

	/**
	 * Calculates the length of the longest surname in the filtered records.
	 * 
	 * @param records
	 *            Filtered records ready to print.
	 * @return Length of the longest surname.
	 */
	public static int longestSurname(List<StudentRecord> records) {
		int longestName = 0;
		for (StudentRecord record : records) {
			if (record.getLastName().length() > longestName) {
				longestName = record.getLastName().length();
			}
		}
		return longestName;
	}

	/**
	 * Calculates the length of the longest first name in the filtered records.
	 * 
	 * @param records
	 *            Filtered records ready to print.
	 * @return Length of the longest first name.
	 */
	public static int longestName(List<StudentRecord> records) {
		int longestName = 0;
		for (StudentRecord record : records) {
			if (record.getFirstName().length() > longestName) {
				longestName = record.getFirstName().length();
			}
		}
		return longestName;
	}

	/**
	 * Method that formats the filtered records into an adequate output and prints
	 * them to the console.
	 * 
	 * @param records
	 *            Filtered records.
	 * @param longestSurname
	 *            Length of the longest surname.
	 * @param longestName
	 *            Length of the longest first name.
	 */
	public static void formatAndPrint(List<StudentRecord> records, int longestSurname, int longestName) {
		StringBuilder sb = new StringBuilder();
		// first frame
		sb.append("+");
		for (int i = 0; i < records.get(0).getJmbag().length() + 2; i++) {
			sb.append("=");
		}
		sb.append("+");
		for (int i = 0; i < longestSurname + 2; i++) {
			sb.append("=");
		}
		sb.append("+");
		for (int i = 0; i < longestName + 2; i++) {
			sb.append("=");
		}
		sb.append("+");
		sb.append("===+\n");

		// data
		for (StudentRecord record : records) {
			sb.append("| ");
			sb.append(record.getJmbag());
			sb.append(" | ");
			sb.append(record.getLastName());
			for (int i = record.getLastName().length(); i < longestSurname; i++) {
				sb.append(" ");
			}
			sb.append(" | ");
			sb.append(record.getFirstName());
			for (int i = record.getFirstName().length(); i < longestName; i++) {
				sb.append(" ");
			}
			sb.append(" | ");
			sb.append(record.getFinalGrade());
			sb.append(" |\n");
		}

		// last frame
		sb.append("+");
		for (int i = 0; i < records.get(0).getJmbag().length() + 2; i++) {
			sb.append("=");
		}
		sb.append("+");
		for (int i = 0; i < longestSurname + 2; i++) {
			sb.append("=");
		}
		sb.append("+");
		for (int i = 0; i < longestName + 2; i++) {
			sb.append("=");
		}
		sb.append("+");
		sb.append("===+\n");

		System.out.println(sb.toString());
	}

}
