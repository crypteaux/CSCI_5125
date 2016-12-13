import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
  public static final Connection connect() throws SQLException{
    String dbLocation = "@dbsvcs.cs.uno.edu:1521:" + "orcl";
		String url = "jdbc:oracle:thin" + ':' + dbLocation;
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection conn = DriverManager.getConnection(url, "dldekerl", "4RPFv9Kh");
    return conn;
  }
}
