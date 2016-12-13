import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobProfileDAO {
	private Connection connection;

	public JobProfileDAO(Connection connection) {
		this.connection = connection;
	}

	public int add(JobProfile jobProfile) throws SQLException {
		int result = 0;
		PreparedStatement stmt = connection.prepareStatement(
			" INSERT INTO job_profile " +
			"   VALUES (?, ?, ?, ? ) "
		);
		stmt.setString(1, jobProfile.getCode());
		stmt.setString(2, jobProfile.getTitle());
		stmt.setString(3, jobProfile.getDescription());
		stmt.setDouble(4, jobProfile.getPay());
		result = stmt.executeUpdate();
		return result;
	}

	public int update(JobProfile jobProfile) throws SQLException {
		int result = 0;
		PreparedStatement stmt = connection.prepareStatement(
			" UPDATE job_profile " +
			" SET title = ?, " +
			"     description = ?, " +
			"     avg_pay = ? " +
			" WHERE jp_code = ? "
		);

		stmt.setString(1, jobProfile.getTitle());
		stmt.setString(2, jobProfile.getDescription());
		stmt.setDouble(3, jobProfile.getPay());
		stmt.setString(4, jobProfile.getCode());

		result = stmt.executeUpdate();
		return result;
	}

	public int delete(String jp_code) throws SQLException {
		int result = 0;
		PreparedStatement stmt = connection.prepareStatement(
			"DELETE FROM job_profile WHERE jp_code = ?"
		);
		stmt.setString(1, jp_code);
		result = stmt.executeUpdate();
		return result;
	}

	public ResultSet queryAll() throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(
			"SELECT * FROM job_profile"
		);
		ResultSet result = stmt.executeQuery();
		return result;
	}

}
