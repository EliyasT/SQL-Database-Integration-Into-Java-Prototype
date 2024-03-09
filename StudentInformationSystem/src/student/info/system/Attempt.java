package student.info.system;

//This class is a simple Data Structure to hold the Values of a single Attempt.
public class Attempt {

	public int _year;
	public int _term;
	public String _courseName;
	public int _grade;
	public String _professor;

	public Attempt(int year, int term, String courseName, int grade, String professor) {
		_year = year;
		_term = term;
		_courseName = courseName;
		_grade = grade;
		_professor = professor;
	}

}

