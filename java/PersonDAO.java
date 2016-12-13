import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
	private Connection connection;

	public PersonDAO(Connection connection) {
		this.connection = connection;
	}

	public int add(Person person) throws SQLException {
		int result = 0;
		PreparedStatement stmt = connection.prepareStatement(
			" INSERT INTO PERSON " +
			"   VALUES (?, ?, ?, ?) "
		);
		stmt.setString(1, person.getPId());
		stmt.setString(2, person.getName());
		stmt.setString(3, person.getEmail());
		stmt.setString(4, person.getGender());
		result = stmt.executeUpdate();
		return result;
	}

	public int update(Person person) throws SQLException {
		int result = 0;
		PreparedStatement stmt = connection.prepareStatement(
			" UPDATE person " +
			" SET name = ?, " +
			"     email = ?, " +
			"     gender = ? " +
			" WHERE per_id = ? "
		);

		stmt.setString(1, person.getName());
		stmt.setString(2, person.getEmail());
		stmt.setString(3, person.getGender());
		stmt.setString(4, person.getPId());
		result = stmt.executeUpdate();
		System.out.println(result);
		return result;
	}

	public int delete(String per_id) throws SQLException {
		int result = 0;
		PreparedStatement stmt = connection.prepareStatement(
			"DELETE FROM person WHERE per_id = ?"
		);
		stmt.setString(1, per_id);
		result = stmt.executeUpdate();
		return result;
	}

	public ResultSet queryAll() throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(
			"SELECT * FROM person"
		);
		ResultSet result = stmt.executeQuery();
		return result;
	}

}
