import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;

public class BusinessRunner {
  private CourseDAO courseDAO;
  private Connection conn;

  private Scanner reader = new Scanner(System.in);

  public BusinessRunner(Connection conn) {
    this.conn = conn;
  }

  public void acceptEmployee() throws SQLException {
    System.out.println("Please input the individual's id: ");
    String id = reader.nextLine();
    System.out.println("Please input the prospective job's code: ");
    String job_code = reader.nextLine();

    ResultSet rs = checkSkills(id, job_code);
    if (rs.next()) {
      System.out.println("Sorry, but this individual is missing the following skills: ");
      rs.beforeFirst();
      print(rs);
      System.out.println();
    } else {
      rs = checkCourses(id, job_code);
      if (rs.next()) {
        System.out.println("Sorry, but this individual is missing the following courses: ");
        rs.beforeFirst();
        print(rs);
        System.out.println();
      } else {
        System.out.println("\nThe individual is eligible. Assigning the job now... ");
        int result = addEmployee(id, job_code);
        if (result > 0) {
          System.out.println("\nJob assigned!");
        } else {
          System.out.println("\nSorry, but there was an error. Please try again.");
        }
      }
    }
  }

  public void findJob() {
    System.out.println("Sorry, but this process is not yet complete...");
  }

  public void findWorkers() {
    System.out.println("Sorry, but this process is not yet complete...");
  }

  public void evaluateOpportunities() {
    System.out.println("Sorry, but this process is not yet complete...");
  }

  private ResultSet checkSkills(String id, String job_code) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "(SELECT skill_code " +
      " FROM job NATURAL JOIN requires " +
      " WHERE job_code = ?) " +
      "MINUS " +
      "(SELECT skill_code " +
      " FROM person NATURAL JOIN has " +
      " WHERE per_id = ?) ",
      ResultSet.TYPE_SCROLL_INSENSITIVE,
      ResultSet.CONCUR_UPDATABLE);
    stmt.setString(1, job_code);
    stmt.setString(2, id);
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  private ResultSet checkCourses(String id, String job_code) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "WITH required_courses AS ( " +
      "  SELECT DISTINCT c_code " +
      "  FROM job NATURAL JOIN job_profile NATURAL JOIN requires NATURAL JOIN covers " +
      "  WHERE job_code = ? " +
      "  UNION " +
      "  SELECT DISTINCT c_code  " +
      "  FROM job NATURAL JOIN job_profile NATURAL JOIN requires_certificate NATURAL JOIN gives_certificate " +
      "  WHERE job_code = ? " +
      "), " +
      "missing_courses AS ( " +
      "  SELECT c_code " +
      "  FROM required_courses " +
      "  MINUS " +
      "  SELECT c_code " +
      "  FROM takes " +
      "  WHERE per_id = ? " +
      ") " +
      " " +
      "SELECT * " +
      "FROM missing_courses ",
      ResultSet.TYPE_SCROLL_INSENSITIVE,
      ResultSet.CONCUR_UPDATABLE);
    stmt.setString(1, job_code);
    stmt.setString(2, job_code);
    stmt.setString(3, id);
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public int addEmployee(String id, String job_code) throws SQLException {
    int result = 0;
    PreparedStatement stmt = conn.prepareStatement(
      " INSERT INTO works " +
      "   VALUES (?, ?) "
    );
    stmt.setString(1, id);
    stmt.setString(2, job_code);
    result = stmt.executeUpdate();
    return result;
  }

  private void print(ResultSet rs) throws SQLException {
    ResultSetMetaData rsmd = rs.getMetaData();
    int columnsNumber = rsmd.getColumnCount();
    while (rs.next()) {
      for (int i = 1; i <= columnsNumber; i++) {
        if (i > 1) System.out.print(",  ");
        String columnValue = rs.getString(i);
        System.out.print(columnValue + " " + rsmd.getColumnName(i));
      }
      System.out.println("");
    }
  }

}
