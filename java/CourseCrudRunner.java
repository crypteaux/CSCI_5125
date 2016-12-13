import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class CourseCrudRunner {
  private CourseDAO courseDAO;
  private Connection conn;

  private Scanner reader;

  public CourseCrudRunner(Connection conn) {
    courseDAO = new CourseDAO(conn);
    reader = new Scanner(System.in);
  }

  public void menu() throws SQLException {
    Boolean quit = false;
    while (!quit) {
      System.out.println("1. Insert a course\n2. Update a course\n3. Delete a course\n4. Query all");
      System.out.println("Please choose a task or type -1 to quit: ");
      System.out.print("> ");
      int n = reader.nextInt();
      switch(n) {
        case -1:
          quit = true;
          break;
        case 1:
          insertCourse();
          break;
        case 2:
          updateCourse();
          break;
        case 3:
          deleteCourse();
          break;
        case 4:
          queryAll();
          break;
        default:
          break;
      }
    }
  }

  private void insertCourse() throws SQLException {
    Course course = new Course();
    getCourseFields(course);
    courseDAO.add(course);
  }

  private void updateCourse() throws SQLException {
    Course course = new Course();
    getCourseFields(course);
    courseDAO.update(course);
  }

  private void deleteCourse() throws SQLException {
    Course course = new Course();
    System.out.println("Please input a course code: ");
    System.out.print("> ");
    reader.nextLine();
    String c_code = reader.nextLine();
    courseDAO.delete(c_code);
  }

  private void queryAll() throws SQLException {
    print(courseDAO.queryAll());
  }

  private void getCourseFields(Course course) {
    System.out.println("Please input a course code: ");
    System.out.print("> ");
    reader.nextLine();
    course.setCode(reader.nextLine());
    System.out.println("Please input a course title: ");
    System.out.print("> ");
    course.setTitle(reader.nextLine());
    System.out.println("Please input a course description: ");
    System.out.print("> ");
    course.setDescription(reader.nextLine());
    System.out.println("Please input a course level: ");
    System.out.print("> ");
    course.setLevel(reader.nextLine());
    System.out.println("Please input a course status: ");
    System.out.print("> ");
    course.setStatus(reader.nextLine());
    System.out.println("Please input a course retail price: ");
    System.out.print("> ");
    course.setRetailPrice(reader.nextInt());
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
