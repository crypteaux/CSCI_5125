import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.util.Scanner;

public class PromptRunner {
  private Scanner reader;
  private TestQueries test;

  public PromptRunner(Connection conn) {
    System.out.println("Loading...");
    reader = new Scanner(System.in);
    test = new TestQueries(conn);
  }

  public void promptOne() throws SQLException {
    System.out.println("Please input the company name: ");
    System.out.print("> ");
    String comp_name = reader.nextLine();
    ResultSet rs = test.one(comp_name);
    print(rs);
  }

  public void promptTwo() throws SQLException {
    System.out.println("Please input the company name: ");
    System.out.print("> ");
    String comp_name = reader.nextLine();
    ResultSet rs = test.two(comp_name);
    print(rs);
  }

  public void promptThree() throws SQLException {
    ResultSet rs = test.three();
    print(rs);
  }

  public void promptFour() throws SQLException {
    System.out.println("Please input the person's name: ");
    System.out.print("> ");
    String name = reader.nextLine();
    ResultSet rs = test.four(name);
    print(rs);
  }

  public void promptFive() throws SQLException {
    System.out.println("Please input the person's id: ");
    System.out.print("> ");
    String id = reader.nextLine();
    ResultSet rs = test.five(id);
    print(rs);
  }

  public void promptSix() throws SQLException {
    System.out.println("Please input the person's id: ");
    System.out.print("> ");
    String id = reader.nextLine();
    ResultSet rs = test.six(id);
    print(rs);
  }

  public void promptSeven() throws SQLException {
    System.out.println("Please input a Job Profile Code: ");
    System.out.print("> ");
    String jp_code = reader.nextLine();
    ResultSet rs = test.seven(jp_code);
    print(rs);
  }

  public void promptEight() throws SQLException {
    System.out.println("Please input the job code: ");
    System.out.print("> ");
    String job_code = reader.nextLine();
    System.out.println("Please input the person's id: ");
    System.out.print("> ");
    String per_id = reader.nextLine();
    ResultSet rs = test.eight(job_code, per_id);
    print(rs);
  }
//revisit
  public void promptNine() throws SQLException {
    System.out.println("Please input the job code: ");
    System.out.print("> ");
    String job_code = reader.nextLine();
    System.out.println("Please input a person's id: ");
    System.out.print("> ");
    String per_id = reader.nextLine();
    ResultSet rs = test.nine(job_code, per_id);
    print(rs);
  }

  public void promptTen() throws SQLException {
    System.out.println("Please input the job code: ");
    System.out.print("> ");
    String job_code = reader.nextLine();
    System.out.println("Please input the person's id: ");
    System.out.print("> ");
    String per_id = reader.nextLine();
    ResultSet rs = test.ten(job_code, per_id);
    print(rs);
  }
//Revisit
  public void promptEleven() throws SQLException {
    System.out.println("Please input the job code: ");
    System.out.print("> ");
    String job_code = reader.nextLine();
    System.out.println("Please input a person's id: ");
    System.out.print("> ");
    String per_id = reader.nextLine();
    ResultSet rs = test.nine(job_code, per_id);
    print(rs);
  }

  public void promptTwelve() throws SQLException {
    System.out.println("Please input the job code: ");
    System.out.print("> ");
    String job_code = reader.nextLine();
    System.out.println("Please input the person's id: ");
    System.out.print("> ");
    String per_id = reader.nextLine();
    ResultSet rs = test.twelve(job_code, per_id);
    print(rs);
  }

  public void promptThirteen() throws SQLException {
    System.out.println("Please input the Person's ID: ");
    System.out.print("> ");
    String per_id = reader.nextLine();
    ResultSet rs = test.thirteen(per_id);
    print(rs);
  }

  public void promptFourteen() throws SQLException {
    System.out.println("Please input the person's id: ");
    System.out.print("> ");
    String per_id = reader.nextLine();
    ResultSet rs = test.fourteen(per_id);
    print(rs);
  }

  public void promptFifteen() throws SQLException {
    System.out.println("Please input the job profile code: ");
    System.out.print("> ");
    String jp_code = reader.nextLine();
    ResultSet rs = test.fifteen(jp_code);
    print(rs);
  }

  public void promptSixteen() throws SQLException {
    System.out.println("Please input the job profile code: ");
    System.out.print("> ");
    String jp_code = reader.nextLine();
    ResultSet rs = test.sixteen(jp_code);
    print(rs);
  }

  //Revisit
  public void promptSeventeen() throws SQLException {
    System.out.println("Please input the job profile code: ");
    System.out.print("> ");
    String job_code = reader.nextLine();
    ResultSet rs = test.seventeen(job_code);
    print(rs);
  }

  public void promptEighteen() throws SQLException {
    System.out.println("Please input the Job Profile Code: ");
    System.out.print("> ");
    String jp_code = reader.nextLine();
    ResultSet rs = test.eighteen(jp_code);
    print(rs);
  }

  public void promptNineteen() throws SQLException {
    System.out.println("Please input the Job Profile Code: ");
    System.out.print("> ");
    String jp_code = reader.nextLine();
    System.out.println("Please input a missed skills number: ");
    System.out.print("> ");
    String k = reader.nextLine();
    ResultSet rs = test.nineteen(jp_code, k);
    print(rs);
  }

  public void promptTwenty() throws SQLException {
    System.out.println("Please input the job profile code: ");
    System.out.print("> ");
    String jp_code = reader.nextLine();
    ResultSet rs = test.twenty(jp_code);
    print(rs);
  }

  public void promptTwentyone() throws SQLException {
    System.out.println("Please input the Job Profile Code: ");
    System.out.print("> ");
    String jp_code = reader.nextLine();
    ResultSet rs = test.twentyone(jp_code);
    print(rs);
  }

  public void promptTwentyTwo() throws SQLException {
    System.out.println("Please input the job profile code: ");
    System.out.print("> ");
    String jp_code = reader.nextLine();
    ResultSet rs = test.twentytwo(jp_code);
    print(rs);
  }

  public void promptTwentythreea() throws SQLException {
    ResultSet rs = test.twentythreea();
    print(rs);
  }

  public void promptTwentythreeb() throws SQLException {
    ResultSet rs = test.twentythreeb();
    print(rs);
  }

  public void promptTwentyfoura() throws SQLException {
    ResultSet rs = test.twentyfoura();
    print(rs);
  }

  public void promptTwentyfourb() throws SQLException {
    ResultSet rs = test.twentyfourb();
    print(rs);
  }

  public void promptTwentyfive() throws SQLException {
    ResultSet rs = test.twentyfive();
    print(rs);
  }

  public void promptTwentySix() throws SQLException {
    ResultSet rs = test.twentysix();
    print(rs);
  }

  public void promptTwentysevena() throws SQLException {
    ResultSet rs = test.twentysevena();
    print(rs);
  }

  public void promptTwentysevenb() throws SQLException {
    ResultSet rs = test.twentysevenb();
    print(rs);
  }

  public void promptTwentyEight() throws SQLException {
    System.out.println("Please input the job profile code: ");
    System.out.print("> ");
    String jp_code = reader.nextLine();
    System.out.println("Please input the person's id: ");
    System.out.print("> ");
    String id = reader.nextLine();
    ResultSet rs = test.twentyeight(jp_code, id);
    print(rs);
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
