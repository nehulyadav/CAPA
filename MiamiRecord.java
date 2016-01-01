import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MiamiRecord {

	private static List<Student> students;
	
	private static String[] menuContents = {
		"Print All Students",
		"Find Student by ID",
		"Class Average",
		"Quit",
	};
	
	private enum Option {
		PRINT,
		SEARCH,
		AVERAGE,
		QUIT,
		ERROR,
	};
	
	private static Option optionFromInt(int x) {
		switch (x) {
		case 1: return Option.PRINT;
		case 2: return Option.SEARCH;
		case 3: return Option.AVERAGE;
		case 4: return Option.QUIT;
		default: return Option.ERROR;
		}
	}
	
	private static Option menuOptionFromUser() {
		Scanner input = new Scanner(System.in);
		Option option = Option.ERROR;
		
		do {
			try {
				System.out.print("Enter an option: ");
				int userInput = input.nextInt();
				option = optionFromInt(userInput);
			} catch (Exception exception) {
				System.out.println("invalid option");
				input.next();
				option = Option.ERROR;
			}
		} while (option == Option.ERROR);
		
		return option;
	}
	
	private static void promptAndSearchStudentsByID(List<Student> students) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter student id: ");
		String id= input.nextLine();
		
		Student student = findStudentByID(students, id);
		if (student != null) {
			System.out.println(student);
		} else {
			System.out.println("Not found.");
		}
	}
	
	private static Student findStudentByID(List<Student> students, String id) {
		for (Student student : students) {
			if (id.equals(student.getStudentID())) {
				return student;
			}
		}
		
		return null;
	}
	
	private static void printAllStudents(List<Student> students) {
		for (Student student : students) {
			System.out.println(student);
		}
	}
	
	private static void handleOption(Option option) {
		switch (option) {
		case PRINT:
			printAllStudents(students);
			break;
		case SEARCH:
			promptAndSearchStudentsByID(students);
			break;
		case AVERAGE:
			System.out.println("Compute class average!");
			break;
		case QUIT:
			System.out.println("Quit the program!");
			break;
		default:
			break;
		}
	}
	
	private static void printMenu() {
		for (int i = 0; i < menuContents.length; i++) {
			System.out.printf("%d\t%s\n", i + 1, menuContents[i]);
		}
	}
	
	public static ArrayList<Student> studentsFromFile(String fileName)
	throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			ArrayList<Student> students = new ArrayList<Student>();
			
			String line;
			while ((line = reader.readLine()) != null) {
				students.add(new Student(line));
			}
			
			return students;
		}
	}
	
	public static void main(String[] args) {
		try {
			students = studentsFromFile("StudentDB.txt");
			Option option;
			do {
				printMenu();
				option = menuOptionFromUser();
				handleOption(option);
			} while (option != Option.QUIT);
			
		} catch (IOException exception) {
			System.err.printf("%s: unable to process file\n", "StudentDB.txt");
		}
	}
	
}
