import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class JobProfileCrudRunner {
  private JobProfileDAO jobProfileDAO;
  private Connection conn;

  private Scanner reader;

  public JobProfileCrudRunner(Connection conn) {
    jobProfileDAO = new JobProfileDAO(conn);
    reader = new Scanner(System.in);
  }

  public void menu() throws SQLException {
    Boolean quit = false;
    while (!quit) {
      System.out.println("1. Insert a job profile\n2. Update a job profile\n3. Delete a job profile\n4. Query all");
      System.out.println("Please choose a task or type -1 to quit: ");
      System.out.print("> ");
      int n = reader.nextInt();
      switch(n) {
        case -1:
          quit = true;
          break;
        case 1:
          insertJobProfile();
          break;
        case 2:
          updateJobProfile();
          break;
        case 3:
          deleteJobProfile();
          break;
        case 4:
          queryAll();
          break;
        default:
          break;
      }
    }
  }

  private void insertJobProfile() throws SQLException {
    JobProfile jobProfile = new JobProfile();
    getJobProfileFields(jobProfile);
    jobProfileDAO.add(jobProfile);
  }

  private void updateJobProfile() throws SQLException {
    JobProfile jobProfile = new JobProfile();
    getJobProfileFields(jobProfile);
    jobProfileDAO.update(jobProfile);
  }

  private void deleteJobProfile() throws SQLException {
    JobProfile jobProfile = new JobProfile();
    System.out.println("Please input a job profile code: ");
    System.out.print("> ");
    reader.nextLine();
    String jp_code = reader.nextLine();
    jobProfileDAO.delete(jp_code);
  }

  private void queryAll() throws SQLException {
    print(jobProfileDAO.queryAll());
  }

  private void getJobProfileFields(JobProfile jobProfile) {
    System.out.println("Please input a job profile code: ");
    System.out.print("> ");
    reader.nextLine();
    jobProfile.setCode(reader.nextLine());
    System.out.println("Please input a job profile title: ");
    System.out.print("> ");
    jobProfile.setTitle(reader.nextLine());
    System.out.println("Please input a job profile description: ");
    System.out.print("> ");
    jobProfile.setDescription(reader.nextLine());
    System.out.println("Please input a job profile average pay: ");
    System.out.print("> ");
    jobProfile.setPay(reader.nextInt());
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
