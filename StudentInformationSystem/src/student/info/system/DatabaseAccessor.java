package student.info.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//This class is used to access the database. Currently we are using dummy values,
// which are access by using "dummy" as the database connection URL.
public class DatabaseAccessor {

	private Connection _con = null;

	public DatabaseAccessor(String dbUrl, String username, String password) {

		if (dbUrl.equals("dummy")) {
			// when we use "dummy" Entries without DB connection
		} else {
			// the real deal, connecting to the database
			try {
				_con = DriverManager.getConnection(dbUrl, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	   // Task 1.3
    public Student[] getAllStudents() throws SQLException {
        // TODO implement SQL Query to get all students and
        ArrayList<Student> arrayList = new ArrayList<>();

        Statement st = _con.createStatement();

        String query = "Select student.*, Program.Pname FROM Student, "
        		+ "Program WHERE Student.programID= program.programID ";;

        ResultSet _resultSet = st.executeQuery(query);

        while (_resultSet.next()) {
            Student student = new Student(_resultSet.getString("firstName"),
            		_resultSet.getString("lastName"), _resultSet.getInt("studentID"),
            		_resultSet.getString("Pname"));
            arrayList.add(student);
        }

        Student[] result = new Student[arrayList.size()];
        result = arrayList.toArray(result);

        return result;
    }

    // Task 1.4
    public Attempt[] getAttemptsForStudent(Student student) throws SQLException {
        // TODO write a Query that gets all the attempts a student has in the database
        ArrayList<Attempt> arrayList = new ArrayList<>();
        String query =
        		"SELECT attempts.*, course.Cdescription,professor.firstName,professor.lastName"
        		+ " FROM attempts, course "
        		+ "WHERE attempts.courseID = course.courseID AND attempts.studentID"
        				+"AND attempts.employeeNumber = professor.employeeNumber " +
                        "AND attempts.studentID ";

        Statement st = _con.createStatement();

        ResultSet _resultSet = st.executeQuery(query);

        while (_resultSet.next()) {
            Attempt attempt = new Attempt(_resultSet.getInt("Ayear"), _resultSet.getInt("term"),
            		_resultSet.getString("Cdescription"), _resultSet.getInt("grade"),
            		(_resultSet.getString("firstName") + " "+ _resultSet.getString("lastName")));
            arrayList.add(attempt);
        }

        Attempt[] result = new Attempt[arrayList.size()];
        result = arrayList.toArray(result);
        return result;
    }
}
