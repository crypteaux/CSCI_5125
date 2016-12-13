import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;
import java.sql.Connection;

public class Runner {

  private static Scanner reader = new Scanner(System.in);
  private static Connection conn;

  public static void main(String[] args) throws SQLException {
    System.out.println("Connecting to database...");
    try {
      conn = Connect.connect();
    } catch(SQLException e) {
      e.printStackTrace();
    }
    Boolean quit = false;
    while (!quit) {
      System.out.println("1. Query the database\n2. Update the database\n3. Complete a business process\n4. Clear, setup, and seed database");
      System.out.println("Please choose a task or type -1 to quit: ");
      System.out.print("> ");
      int n = reader.nextInt();
      switch(n) {
        case -1:
          quit = true;
          break;
        case 1:
          queries();
          break;
        case 2:
          crud();
          break;
        case 3:
          business();
          break;
        case 4:
          reset();
          break;
        default:
          break;
      }
    }
  }

  public static void queries() throws SQLException {
    Boolean quit = false;
    while (!quit) {
      PromptRunner run = new PromptRunner(conn);
      System.out.println(
      "1. List a company’s workers by names.\n " +
      "2. List a company’s staff by salary in descending order.\n " +
      "3. List companies’ labor cost (total salaries and wage rates by 1920 hours) in descending order.\n " +
      "4. Find all the jobs a person is currently holding and worked in the past.\n " +
      "5. List a person’s knowledge/skills in a readable format.\n " +
      "6. List the skill gap of a worker between his/her job(s) and his/her skills.\n " +
      "7. List the required knowledge/skills of a job profile in a readable format.\n " +
      "8. List a person’s missing knowledge/skills for a specific job in a readable format.\n " +
      "9. List the courses (course id and title) that each alone teaches all the missing knowledge/skills for a person to pursue a specific job.\n " +
      "10. Suppose the skill gap of a worker and the requirement of a desired job can be covered by one course. Find the“quickest” solution for this worker.\n " +
      "11. Find the cheapest course to make up one’s skill gap by showing the course to take and the cost (of the section price).\n " +
      "12. If query #9 returns nothing, then find the course sets that their combination covers all the missing knowledge/ skills for a person to pursue a specific job. The considered course sets will not include more than three courses. If multiple course sets are found, list the course sets (with their course IDs) in the order of the ascending order of the course sets’ total costs.\n " +
      "13. List all the job profiles that a person is qualified for.\n " +
      "14. Find the job with the highest pay rate for a person according to his/her skill qualification.\n " +
      "15. List all the names along with the emails of the persons who are qualified for a job profile.\n " +
      "16. When a company cannot find any qualified person for a job, a secondary solution is to find a person who is almost qualified to the job. Make a “missing-one” list that lists people who miss only one skill for a specified job profile.\n " +
      "17. List the skillID and the number of people in the missing-one list for a given job profile in the ascending order of the people counts.\n " +
      "18. Suppose there is a new job profile that has nobody qualified. List the persons who miss the least number of skills and report the “least number”.\n " +
      "19. For a specified job profile and a given small number k, make a “missing-k” list that lists the people’s IDs and the number of missing skills for the people who miss only up to k skills in the ascending order of missing skills.\n " +
      "20. Given a job profile and its corresponding missing-k list specified in Question 19. Find every skill that is needed by at least one person in the given missing-k list. List each skillID and the number of people who need it in the descending order of the people counts. (required for graduate students only)\n " +
      "21. In a local or national crisis, we need to find all the people who once held a job of the special job-profile identifier.\n " +
      "22. Find all the unemployed people who once held a job of the given job-profile identifier.\n " +
      "231. Find out the biggest employer in terms of number of employees\n " +
      "232. Find the biggest employeer in terms of total amount of salaries and wages paid to employees.\n " +
      "241. Find out the biggest job sector in terms of number of employees\n " +
      "242. Find the biggest job sector in terms of the total amount of salaries and wages paid to employees.\n " +
      "25. Find out the ratio between the people whose earnings increase and those whose earning decrease; find the average rate of earning improvement for the workers in a specific business sector.\n " +
      "26. Find the job profiles that have the most openings due to lack of qualified workers. If there are many opening jobs of a job profile but at the same time there are many qualified jobless people. Then training cannot help fill up this type of job. What we want to find is such a job profile that has the largest difference between vacancies (the unfilled jobs of this job profile) and the number of jobless people who are qualified for this job profile.\n " +
      "271. List the course sets that can together train an unemployed worker for the job profiles that have the most openings due to a lack of skilled workers.\n " +
      "272. List the courses that can provide the most jobless people with skills and certificates necessary to get a job of the job profiles with the most openings due to lack of qualified workers.\n " +
      "28. List all the courses, directly or indirectly required, that a person has to take in order to be qualified for a job of the given profile, according to his/her skills possessed and courses taken.");
      System.out.println("Please choose a query number or type -1 to quit: ");
      System.out.print("> ");
      int n = reader.nextInt();
      switch(n) {
        case -1:
          quit = true;
          break;
        case 1:
          run.promptOne();
          break;
        case 2:
          run.promptTwo();
          break;
        case 3:
          run.promptThree();
          break;
        case 4:
          run.promptFour();
          break;
        case 5:
          run.promptFive();
          break;
        case 6:
          run.promptSix();
          break;
        case 7:
          run.promptSeven();
          break;
        case 8:
          run.promptEight();
          break;
        case 9:
          run.promptNine();
          break;
        case 10:
          run.promptTen();
          break;
        case 11:
          run.promptEleven();
          break;
        case 12:
          run.promptTwelve();
          break;
        case 13:
          run.promptThirteen();
          break;
        case 14:
          run.promptFourteen();
          break;
        case 15:
          run.promptFifteen();
          break;
        case 16:
          run.promptSixteen();
          break;
        case 17:
          run.promptSeventeen();
          break;
        case 18:
          run.promptEighteen();
          break;
        case 19:
          run.promptNineteen();
          break;
        case 20:
          run.promptTwenty();
          break;
        case 21:
          run.promptTwentyone();
          break;
        case 22:
          run.promptTwentyTwo();
          break;
        case 231:
          run.promptTwentythreea();
          break;
        case 232:
          run.promptTwentythreeb();
          break;
        case 241:
          run.promptTwentyfoura();
          break;
        case 242:
          run.promptTwentyfourb();
          break;
        case 25:
          run.promptTwentyfive();
          break;
        case 26:
          run.promptTwentySix();
          break;
        case 271:
          run.promptTwentysevena();
          break;
        case 272:
          run.promptTwentysevenb();
          break;
        case 28:
          run.promptTwentyEight();
          break;
        }
        System.out.println("Would you like to make another query? (y/n) ");
        System.out.print("> ");
        char cont = reader.next().charAt(0);
        if (cont == 'y') {
          // do nothing
        } else {
          quit = true;
        }
      }
    }

    public static void crud() throws SQLException {
      Boolean quit = false;
      while (!quit) {
        System.out.println("1. course\n2. job_profile\n3. job\n4. person");
        System.out.println("Please choose a table to edit or type -1 to quit: ");
        System.out.print("> ");
        int n = reader.nextInt();
        switch(n) {
          case -1:
            quit = true;
            break;
          case 1:
            CourseCrudRunner courseRun = new CourseCrudRunner(conn);
            courseRun.menu();
            break;
          case 2:
            JobProfileCrudRunner jpRun = new JobProfileCrudRunner(conn);
            jpRun.menu();
            break;
          case 3:
            JobCrudRunner jRun = new JobCrudRunner(conn);
            jRun.menu();
            break;
          case 4:
            PersonCrudRunner pRun = new PersonCrudRunner(conn);
            pRun.menu();
            break;
          default:
            break;
        }
      }
    }

    public static void business() throws SQLException {
      Boolean quit = false;
      BusinessRunner runner = new BusinessRunner(conn);
      while (!quit) {
        System.out.println("1. Accept new employee\n2. Look for a job\n3. Find qualified workers\n4. Evaluate opportunities");
        System.out.println("Please choose a task or type -1 to quit: ");
        System.out.print("> ");
        int n = reader.nextInt();
        switch(n) {
          case -1:
            quit = true;
            break;
          case 1:
            runner.acceptEmployee();
            break;
          case 2:
            runner.findJob();
            break;
          case 3:
            runner.findWorkers();
            break;
          case 4:
            runner.evaluateOpportunities();
            break;
          default:
            break;
        }
      }
    }

    public static void reset() throws SQLException {
      Setup setup = new Setup(conn);
      setup.clean();
      setup.setup();
      setup.seed();
    }
}
