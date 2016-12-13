import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
	private Connection connection;

	public CourseDAO(Connection connection) {
		this.connection = connection;
	}

	public int add(Course course) throws SQLException {
		int result = 0;
		PreparedStatement stmt = connection.prepareStatement(
			" INSERT INTO course " +
			"   VALUES (?, ?, ?, ?, ?, ?) "
		);
		stmt.setString(1, course.getCode());
		stmt.setString(2, course.getTitle());
		stmt.setString(3, course.getLevel());
		stmt.setString(4, course.getDescription());
		stmt.setString(5, course.getStatus());
		stmt.setDouble(6, course.getRetailPrice());
		result = stmt.executeUpdate();
		return result;
	}

	public int update(Course course) throws SQLException {
		int result = 0;
		PreparedStatement stmt = connection.prepareStatement(
			" UPDATE course " +
			" SET title = ?, " +
			"     description = ?, " +
			"     course_level = ?, " +
			"     status = ?, " +
			"     retail_price = ? " +
			" WHERE c_code = ? "
		);

		stmt.setString(1, course.getTitle());
		stmt.setString(2, course.getDescription());
		stmt.setString(3, course.getLevel());
		stmt.setString(4, course.getStatus());
		stmt.setDouble(5, course.getRetailPrice());
		stmt.setString(6, course.getCode());
		result = stmt.executeUpdate();
		return result;
	}

	public int delete(String c_code) throws SQLException {
		int result = 0;
		PreparedStatement stmt = connection.prepareStatement(
			"DELETE FROM Course WHERE c_code = ?"
		);
		stmt.setString(1, c_code);
		result = stmt.executeUpdate();
		return result;
	}

	public ResultSet queryAll() throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(
			"SELECT * FROM course"
		);
		ResultSet result = stmt.executeQuery();
		return result;
	}

}
