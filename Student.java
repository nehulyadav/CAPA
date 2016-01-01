import java.util.ArrayList;


public class Student {
	
	private static final String SEPARATOR = "\\|";
	
	private String studentID;
	private String firstName;
	private String lastName;
	private ArrayList<Double> examGrades;
	
	public Student() {
		this(null, null, null);
	}
	
	public Student(String studentID, String firstName, String lastName) {
		setStudentID(studentID);
		setFirstName(firstName);
		setLastName(lastName);
		examGrades = new ArrayList<Double>();
	}
	
	public Student(String recordString) {
		String[] fields = recordString.split(SEPARATOR);
		
		setStudentID(fields[0]);
		setFirstName(fields[1]);
		setLastName(fields[2]);
		
		examGrades = new ArrayList<Double>();
		for (int i = 3; i < fields.length; i++) {
			addExamGrade(Double.parseDouble(fields[i]));
		}
	}
	
	@Override
	public String toString() {
		return String.format("%s\t%s %s\t%.2f", getStudentID(),
				getFirstName(), getLastName(), getAverage());
	}
	
	public void addExamGrade(double grade) {
		examGrades.add(grade);
	}
	
	public ArrayList<Double> getExameGrades() {
		return examGrades;
	}
	
	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getAverage() {
		if (examGrades.isEmpty()) {return 0;}
		
		double sum = 0;
		for (double grade : examGrades) {
			sum += grade;
		}
		
		return sum / examGrades.size();
	}

}
