import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class PersonCrudRunner {
  private PersonDAO personDAO;
  private Scanner reader;

  public PersonCrudRunner(Connection conn) {
    personDAO = new PersonDAO(conn);
    reader = new Scanner(System.in);
  }

  public void menu() throws SQLException {
    Boolean quit = false;
    while (!quit) {
      System.out.println("1. Insert a person \n2. Update a person \n3. Delete a person\n4. Query all");
      System.out.println("Please choose a task or type -1 to quit: ");
      System.out.print("> ");
      int n = reader.nextInt();
      switch(n) {
        case -1:
          quit = true;
          break;
        case 1:
          insertPerson();
          break;
        case 2:
          updatePerson();
          break;
        case 3:
          deletePerson();
          break;
        case 4:
          queryAll();
          break;
        default:
          break;
      }
    }
  }

  private void insertPerson() throws SQLException {
    Person person = new Person();
    getPersonFields(person);
    personDAO.add(person);
  }

  private void updatePerson() throws SQLException {
    Person person = new Person();
    getPersonFields(person);
    personDAO.update(person);
  }

  private void deletePerson() throws SQLException {
    Person person = new Person();
    System.out.println("Please input a person code: ");
    System.out.print("> ");
    reader.nextLine();
    String per_id = reader.nextLine();
    personDAO.delete(per_id);
  }

  private void queryAll() throws SQLException {
    print(personDAO.queryAll());
  }

  private void getPersonFields(Person person) {
    System.out.println("Please input a person id: ");
    System.out.print("> ");
    reader.nextLine();
    person.setPId(reader.nextLine());
    System.out.println("Please input a name ");
    System.out.print("> ");
    person.setName(reader.nextLine());
    System.out.println("Please input a email: ");
    System.out.print("> ");
    person.setEmail(reader.nextLine());
    System.out.println("Please inpute a Gender ");
    System.out.print("> ");
    person.setGender(reader.nextLine());
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
