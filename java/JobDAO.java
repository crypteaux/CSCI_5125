import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {
	private Connection connection;

	public JobDAO(Connection connection) {
		this.connection = connection;
	}

	public int add(Job job) throws SQLException {
		int result = 0;
		PreparedStatement stmt = connection.prepareStatement(
			" INSERT INTO job " +
			"   VALUES (?, ?, ?, ?, ?, ?) "
		);
		stmt.setString(1, job.getCode());
		stmt.setString(2, job.getCompId());
		stmt.setString(3, job.getjpCode());
		stmt.setString(4, job.getType());
    stmt.setString(5, job.getRate());
    stmt.setString(6, job.getPayType());
		System.out.println(job.getPayType());
		result = stmt.executeUpdate();
		System.out.println(result);
		return result;
	}

	public int update(Job job) throws SQLException {
		int result = 0;
		PreparedStatement stmt = connection.prepareStatement(
			" UPDATE job " +
			" SET comp_id = ?, " +
			"     jp_code = ?, " +
			"     type = ?, " +
			"     pay_rate = ?, " +
			"     pay_type = ? " +
			" WHERE job_code = ? "
		);
		stmt.setString(1, job.getCompId());
		stmt.setString(2, job.getjpCode());
		stmt.setString(3, job.getType());
    stmt.setString(4, job.getRate());
    stmt.setString(5, job.getPayType());
		stmt.setString(6, job.getCode());
		result = stmt.executeUpdate();
		return result;
	}

	public int delete(String job_code) throws SQLException {
		int result = 0;
		PreparedStatement stmt = connection.prepareStatement(
			"DELETE FROM job WHERE job_code = ?"
		);
		stmt.setString(1, job_code);
		result = stmt.executeUpdate();
		return result;
	}

	public ResultSet queryAll() throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(
			"SELECT * FROM job"
		);
		ResultSet result = stmt.executeQuery();
		return result;
	}

}
