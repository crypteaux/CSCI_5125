import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class JobCrudRunner {
  private JobDAO jobDAO;
  private Connection conn;

  private Scanner reader;

  public JobCrudRunner(Connection conn) {
    jobDAO = new JobDAO(conn);
    reader = new Scanner(System.in);
  }

  public void menu() throws SQLException {
    Boolean quit = false;
    while (!quit) {
      System.out.println("1. Insert a job \n2. Update a job \n3. Delete a job\n4. Query all");
      System.out.println("Please choose a task or type -1 to quit: ");
      System.out.print("> ");
      int n = reader.nextInt();
      switch(n) {
        case -1:
          quit = true;
          break;
        case 1:
          insertJob();
          break;
        case 2:
          updateJob();
          break;
        case 3:
          deleteJob();
          break;
        case 4:
          queryAll();
          break;
        default:
          break;
      }
    }
  }

  private void insertJob() throws SQLException {
    Job job = new Job();
    getJobFields(job);
    jobDAO.add(job);
  }

  private void updateJob() throws SQLException {
    Job job = new Job();
    getJobFields(job);
    jobDAO.update(job);
  }

  private void deleteJob() throws SQLException {
    Job job = new Job();
    System.out.println("Please input a job code: ");
    System.out.print("> ");
    reader.nextLine();
    String job_code = reader.nextLine();
    jobDAO.delete(job_code);
  }

  private void queryAll() throws SQLException {
    print(jobDAO.queryAll());
  }

  private void getJobFields(Job job) {
    System.out.println("Please input a job code: ");
    System.out.print("> ");
    reader.nextLine();
    job.setCode(reader.nextLine());
    System.out.println("Please input a company Id ");
    System.out.print("> ");
    job.setCompId(reader.nextLine());
    System.out.println("Please input a job profile code: ");
    System.out.print("> ");
    job.setjpCode(reader.nextLine());
    System.out.println("Please input a type(part-time or full-time): ");
    System.out.print("> ");
    job.setType(reader.nextLine());
    System.out.println("Please input a pay rate: ");
    System.out.print("> ");
    job.setRate(reader.nextLine());
    System.out.println("Please input a pay type(wage or salary): ");
    System.out.print("> ");
    job.setPayType(reader.nextLine());
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
