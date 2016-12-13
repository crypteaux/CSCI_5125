import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

public class TestQueries {
  private Connection conn;

  public TestQueries(Connection conn) {
    this.conn = conn;
  }

  public ResultSet testDB() throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "SELECT * " +
      "FROM person ");
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet one(String comp_name) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "SELECT name " +
      "FROM person NATURAL JOIN works NATURAL JOIN company " +
      "WHERE comp_name = ? ");
    stmt.setString(1, comp_name);
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet two(String comp_name) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "SELECT name, pay_rate " +
      "FROM person NATURAL JOIN works NATURAL JOIN job NATURAL JOIN company " +
      "WHERE comp_name = ? " +
      "AND pay_type = 'salary' " +
      "ORDER BY pay_rate DESC ");
    stmt.setString(1, comp_name);
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet three() throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "WITH computed_pay AS ( " +
      "  SELECT comp_id, J.job_code, " +
      "    CASE " +
      "      WHEN J.pay_type = 'salary' THEN J.pay_rate " +
      "      WHEN J.pay_type = 'wage' THEN J.pay_rate * 1920 " +
      "      ELSE NULL " +
      "    END " +
      "    \"Total Pay\" " +
      "  FROM job J " +
      ") " +
      "SELECT comp_id, SUM(\"Total Pay\") AS total_pay " +
      "FROM computed_pay " +
      "GROUP BY comp_id " +
      "ORDER BY total_pay DESC ");
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet four(String name) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "SELECT name, title " +
      "FROM person NATURAL JOIN works NATURAL JOIN job NATURAL JOIN job_profile " +
      "WHERE name = ? " +
      "UNION " +
      "SELECT name, title " +
      "FROM person NATURAL JOIN worked NATURAL JOIN job NATURAL JOIN job_profile " +
      "WHERE name = ? ");
    stmt.setString(1, name);
    stmt.setString(2, name);
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet five(String per_id) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "SELECT title, skill_code " +
      "FROM skill " +
      "NATURAL JOIN has " +
      "WHERE per_id = ? ");
    stmt.setString(1, per_id);
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet six(String per_id) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "(SELECT DISTINCT skill_code " +
      " FROM person NATURAL JOIN works NATURAL JOIN " +
      "      job NATURAL JOIN job_profile NATURAL JOIN " +
      "      requires " +
      " WHERE per_id = ?) " +
      "MINUS " +
      "(SELECT skill_code " +
      " FROM person NATURAL JOIN has " +
      " WHERE per_id = ?) ");
    stmt.setString(1, per_id);
    stmt.setString(2, per_id);
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet seven(String jp_code) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "SELECT skill_code, title, description, skill_level " +
      "FROM requires NATURAL JOIN skill " +
      "WHERE jp_code = ? ");
    stmt.setString(1, jp_code);
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet eight(String job_code, String per_id) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "SELECT title " +
      "FROM skill NATURAL JOIN " +
      "    (SELECT skill_code " +
      "      FROM requires NATURAL JOIN job " +
      "      WHERE job_code = ? " +
      "      MINUS " +
      "      SELECT skill_code " +
      "      FROM has " +
      "      WHERE per_id = ?) ");
    stmt.setString(1, job_code);
    stmt.setString(2, per_id);
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet nine(String job_code, String per_id) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "WITH missing_skills AS ( " +
      "  SELECT skill_code " +
      "  FROM job NATURAL JOIN job_profile NATURAL JOIN requires " +
      "  WHERE job_code = ? " +
      "  MINUS " +
      "  SELECT skill_code " +
      "  FROM person NATURAL JOIN has " +
      "  WHERE per_id = ? " +
      "), " +
      "missing_certificates AS ( " +
      "  SELECT cer_code " +
      "  FROM job NATURAL JOIN job_profile NATURAL JOIN requires_certificate " +
      "  WHERE job_code = ? " +
      "  MINUS " +
      "  SELECT cer_code " +
      "  FROM person NATURAL JOIN has_certificate " +
      "  WHERE per_id = ? " +
      ") " +
      " " +
      "SELECT DISTINCT c_code, title " +
      "FROM course C " +
      "WHERE NOT EXISTS (SELECT skill_code " +
      "                  FROM missing_skills " +
      "                  MINUS " +
      "                  SELECT skill_code " +
      "                  FROM covers J " +
      "                  WHERE C.c_code = J.c_code) " +
      "                  AND NOT EXISTS " +
      "                  (SELECT cer_code " +
      "                  FROM missing_certificates " +
      "                  MINUS " +
      "                  SELECT cer_code " +
      "                  FROM gives_certificate GC " +
      "                  WHERE C.c_code = GC.c_code) ");
    stmt.setString(1, job_code);
    stmt.setString(2, per_id);
    stmt.setString(3, job_code);
    stmt.setString(4, per_id);
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet ten(String job_code, String per_id) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "WITH missing_skills AS ( " +
      "  SELECT skill_code " +
      "  FROM job NATURAL JOIN job_profile NATURAL JOIN requires " +
      "  WHERE job_code = ? " +
      "  MINUS " +
      "  SELECT skill_code " +
      "  FROM person NATURAL JOIN has NATURAL JOIN skill " +
      "  WHERE per_id = ? " +
      "), " +
      "missing_certificates AS ( " +
      "  SELECT cer_code " +
      "  FROM job NATURAL JOIN job_profile NATURAL JOIN requires_certificate " +
      "  WHERE job_code = ? " +
      "  MINUS " +
      "  SELECT cer_code " +
      "  FROM person NATURAL JOIN has_certificate " +
      "  WHERE per_id = ? " +
      "), " +
      "complete_courses AS ( " +
      "  SELECT DISTINCT c_code, title " +
      "  FROM course C " +
      "  WHERE NOT EXISTS (SELECT skill_code " +
      "                    FROM missing_skills " +
      "                    MINUS " +
      "                    SELECT skill_code " +
      "                    FROM covers J " +
      "                    WHERE C.c_code = J.c_code) " +
      "                    AND NOT EXISTS " +
      "                    (SELECT cer_code " +
      "                    FROM missing_certificates " +
      "                    MINUS " +
      "                    SELECT cer_code " +
      "                    FROM gives_certificate GC " +
      "                    WHERE C.c_code = GC.c_code) " +
      ") " +
      "SELECT c_code, title, sec_no, complete_date " +
      "FROM complete_courses NATURAL JOIN section " +
      "WHERE complete_date = (SELECT MIN(complete_date) " +
      "                       FROM complete_courses NATURAL JOIN section) ");
    stmt.setString(1, job_code);
    stmt.setString(2, per_id);
    stmt.setString(3, job_code);
    stmt.setString(4, per_id);
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet eleven(String job_code, String per_id) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "WITH missing_skills AS ( " +
      "  SELECT skill_code " +
      "  FROM job NATURAL JOIN job_profile NATURAL JOIN requires " +
      "  WHERE job_code = 5 " +
      "  MINUS " +
      "  SELECT skill_code " +
      "  FROM person NATURAL JOIN has NATURAL JOIN skill " +
      "  WHERE per_id = 5 " +
      "), " +
      "missing_certificates AS ( " +
      "  SELECT cer_code " +
      "  FROM job NATURAL JOIN job_profile NATURAL JOIN requires_certificate " +
      "  WHERE job_code = 5 " +
      "  MINUS " +
      "  SELECT cer_code " +
      "  FROM person NATURAL JOIN has_certificate " +
      "  WHERE per_id = 5 " +
      "), " +
      "complete_courses AS ( " +
      "  SELECT DISTINCT c_code, title " +
      "  FROM course C " +
      "  WHERE NOT EXISTS (SELECT skill_code " +
      "                    FROM missing_skills " +
      "                    MINUS " +
      "                    SELECT skill_code " +
      "                    FROM covers J " +
      "                    WHERE C.c_code = J.c_code) " +
      "                    AND NOT EXISTS " +
      "                    (SELECT cer_code " +
      "                    FROM missing_certificates " +
      "                    MINUS " +
      "                    SELECT cer_code " +
      "                    FROM gives_certificate GC " +
      "                    WHERE C.c_code = GC.c_code) " +
      ") " +
      " " +
      "SELECT c_code, sec_no, price " +
      "FROM complete_courses NATURAL JOIN section " +
      "WHERE price = (SELECT MIN(price) " +
      "                       FROM complete_courses NATURAL JOIN section) ");
    stmt.setString(1, job_code);
    stmt.setString(2, per_id);
    stmt.setString(3, job_code);
    stmt.setString(4, per_id);
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  // couldn't get provided code to work

  public ResultSet twelve(String per_id, String job_code) throws SQLException {
    PreparedStatement stmt;

    stmt = conn.prepareStatement(
      "INSERT INTO CourseSet " +
      "SELECT CourseSet_seq.NEXTVAL, C1.c_code, C2.c_code, null, 2 " +
      "FROM Course C1, Course C2 " +
      "WHERE C1.c_code < C2.c_code "
    );
    stmt.executeQuery();

    stmt = conn.prepareStatement(
      "INSERT INTO CourseSet " +
      "SELECT CourseSet_seq.NEXTVAL, C1.c_code, C2.c_code, C3.c_code, 3 " +
      "FROM Course C1, Course C2, Course C3 " +
      "WHERE C1.c_code < C2.c_code AND C2.c_code < C3.c_code "
    );
    stmt.executeQuery();

    stmt = conn.prepareStatement(
      "WITH CourseSet_Skill(csetID, skill_code) AS ( " +
      "  SELECT csetID, skill_code " +
      "  FROM CourseSet CSet JOIN covers CS ON CSet.c_code1=CS.c_code " +
      "  UNION " +
      "  SELECT csetID, skill_code " +
      "  FROM CourseSet CSet JOIN covers CS ON CSet.c_code2=CS.c_code " +
      "  UNION " +
      "  SELECT csetID, skill_code " +
      "  FROM CourseSet CSet JOIN covers CS ON CSet.c_code3=CS.c_code " +
      "), " +
      "CourseSet_Certificate(csetID, cer_code) AS ( " +
      "  SELECT csetID, cer_code " +
      "  FROM CourseSet CSet JOIN gives_certificate CS ON CSet.c_code1=CS.c_code " +
      "  UNION " +
      "  SELECT csetID, cer_code " +
      "  FROM CourseSet CSet JOIN gives_certificate CS ON CSet.c_code2=CS.c_code " +
      "  UNION " +
      "  SELECT csetID, cer_code " +
      "  FROM CourseSet CSet JOIN gives_certificate CS ON CSet.c_code3=CS.c_code " +
      "), " +
      "missing_skills AS ( " +
      "  SELECT skill_code " +
      "  FROM job NATURAL JOIN job_profile NATURAL JOIN requires " +
      "  WHERE job_code = ? " +
      "  MINUS " +
      "  SELECT skill_code " +
      "  FROM person NATURAL JOIN has NATURAL JOIN skill " +
      "  WHERE per_id = ? " +
      "), " +
      "missing_certificates AS ( " +
      "  SELECT cer_code " +
      "  FROM job NATURAL JOIN job_profile NATURAL JOIN requires_certificate " +
      "  WHERE job_code = ? " +
      "  MINUS " +
      "  SELECT cer_code " +
      "  FROM person NATURAL JOIN has_certificate " +
      "  WHERE per_id = ? " +
      "), " +
      "Cover_CSet(csetID, cs_size) AS ( " +
      "  SELECT csetID, cs_size " +
      "  FROM CourseSet CSet " +
      "  WHERE NOT EXISTS (SELECT skill_code " +
      "                    FROM missing_skills " +
      "                    MINUS " +
      "                    SELECT skill_code " +
      "                    FROM CourseSet_Skill CSSk " +
      "                    WHERE CSSk.csetID = CSet.csetID) " +
      "                    AND NOT EXISTS " +
      "                    (SELECT cer_code " +
      "                    FROM missing_certificates " +
      "                    MINUS " +
      "                    SELECT cer_code " +
      "                    FROM CourseSet_Certificate CSC " +
      "                    WHERE CSC.csetID = CSet.csetID) " +
      ") " +
      "SELECT DISTINCT c_code1, c_code2, c_code3 " +
      "FROM Cover_CSet NATURAL JOIN CourseSet " +
      "WHERE cs_size = (SELECT MIN(cs_size) FROM Cover_CSet) "
    );
    stmt.setString(1, per_id);
    stmt.setString(2, job_code);
    stmt.setString(3, per_id);
    stmt.setString(4, job_code);
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet thirteen(String per_id) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "SELECT jp_code, title " +
      "FROM job_profile JP " +
      "WHERE NOT EXISTS (SELECT skill_code " +
      "                  FROM requires R " +
      "                  WHERE JP.jp_code = R.jp_code " +
      "                  MINUS " +
      "                  SELECT skill_code " +
      "                  FROM has H " +
      "                  WHERE per_id = ?) " +
      "                  AND NOT EXISTS " +
      "                  (SELECT cer_code " +
      "                   FROM requires_certificate RC " +
      "                   WHERE JP.jp_code = RC.jp_code " +
      "                   MINUS " +
      "                   SELECT cer_code " +
      "                   FROM has_certificate HC " +
      "                   WHERE HC.per_id = ?) ");
    stmt.setString(1, per_id);
    stmt.setString(2, per_id);
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet fourteen(String per_id) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "WITH jp_qualified AS ( " +
      "  SELECT jp_code " +
      "  FROM job_profile JP " +
      "  WHERE NOT EXISTS (SELECT skill_code " +
      "                    FROM requires R " +
      "                    WHERE JP.jp_code = R.jp_code " +
      "                    MINUS " +
      "                    SELECT skill_code " +
      "                    FROM has H " +
      "                    WHERE per_id = ?) " +
      "                    AND NOT EXISTS " +
      "                    (SELECT cer_code " +
      "                     FROM requires_certificate RC " +
      "                     WHERE JP.jp_code = RC.jp_code " +
      "                     MINUS " +
      "                     SELECT cer_code " +
      "                     FROM has_certificate HC " +
      "                     WHERE HC.per_id = ?) " +
      ") " +
      " " +
      "SELECT job_code, pay_rate " +
      "FROM jp_qualified NATURAL JOIN job " +
      "WHERE pay_rate = (SELECT MAX(pay_rate) " +
      "                  FROM jp_qualified NATURAL JOIN job) ");
    stmt.setString(1, per_id);
    stmt.setString(2, per_id);
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet fifteen(String jp_code) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "SELECT per_id, name, email " +
      "FROM person P " +
      "WHERE NOT EXISTS (SELECT skill_code " +
      "                  FROM requires R " +
      "                  WHERE R.jp_code = ? " +
      "                  MINUS " +
      "                  SELECT skill_code " +
      "                  FROM has H " +
      "                  WHERE P.per_id=H.per_id) " +
      "                  AND NOT EXISTS " +
      "                  (SELECT cer_code " +
      "                   FROM requires_certificate RC " +
      "                   WHERE RC.jp_code = ? " +
      "                   MINUS " +
      "                   SELECT cer_code " +
      "                   FROM has_certificate HC " +
      "                   WHERE P.per_id=HC.per_id) ");
    stmt.setString(1, jp_code);
    stmt.setString(2, jp_code);
    ResultSet rset = stmt.executeQuery();
    return rset;
  }
  public ResultSet sixteen(String jp_code) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "SELECT per_id, name " +
      "FROM person P " +
      "WHERE 1 = (SELECT COUNT(skill_code) " +
      "                  FROM (SELECT skill_code " +
      "                        FROM job_profile NATURAL JOIN requires " +
      "                        WHERE jp_code = ? " +
      "                        MINUS " +
      "                        SELECT skill_code " +
      "                        FROM has H " +
      "                        WHERE H.per_id = P.per_id)) ");
    stmt.setString(1, jp_code);
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet seventeen(String jp_code) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "WITH skills_list AS ( " +
      "  SELECT skill_code " +
      "  FROM requires " +
      "  WHERE jp_code = ? " +
      "), " +
      "missing_one AS ( " +
      "  SELECT per_id, name " +
      "  FROM person P " +
      "  WHERE 1 = (SELECT count(skill_code) " +
      "                    FROM (SELECT skill_code " +
      "                          FROM job_profile NATURAL JOIN requires " +
      "                          WHERE jp_code = ? " +
      "                          MINUS " +
      "                          SELECT skill_code " +
      "                          FROM has H " +
      "                          WHERE H.per_id = P.per_id)) " +
      ") " +
      " " +
      "SELECT skill_code, COUNT(DISTINCT per_id) " +
      "FROM (SELECT per_id, skill_code " +
      "      FROM missing_one M, skills_list S " +
      "      MINUS " +
      "      SELECT per_id, skill_code " +
      "      FROM missing_one NATURAL JOIN has) " +
      "GROUP BY skill_code ");
    stmt.setString(1, jp_code);
    stmt.setString(2, jp_code);
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet eighteen(String jp_code) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "WITH required_skills AS ( " +
      "  SELECT skill_code " +
      "  FROM requires " +
      "  WHERE jp_code = ? " +
      "), " +
      "missing_skills (per_id, num_skills_missed) AS ( " +
      "  SELECT per_id, COUNT(skill_code) as num_skills_missed " +
      "  FROM person P, required_skills J " +
      "  WHERE EXISTS (SELECT skill_code " +
      "                FROM required_skills " +
      "                WHERE skill_code = J.skill_code " +
      "                MINUS " +
      "                SELECT skill_code " +
      "                FROM HAS " +
      "                WHERE per_id = P.per_id) " +
      "  GROUP BY per_id " +
      ") " +
      " " +
      "SELECT per_id, num_skills_missed " +
      "FROM missing_skills " +
      "WHERE num_skills_missed = (SELECT MIN(num_skills_missed) " +
      "                           FROM missing_skills) ");
    stmt.setString(1, jp_code);
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet nineteen(String jp_code, String k) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "WITH needed_skill_count AS ( " +
      "  SELECT per_id, " +
      "        (SELECT COUNT(skill_code) " +
      "          FROM (SELECT skill_code " +
      "                FROM job_profile NATURAL JOIN requires " +
      "                WHERE jp_code = ? " +
      "                MINUS " +
      "                SELECT skill_code " +
      "                FROM has H " +
      "                WHERE H.per_id = P.per_id)) " +
      "          AS needed_skill " +
      "  FROM person P " +
      ") " +
      " " +
      "SELECT * " +
      "FROM needed_skill_count " +
      "WHERE needed_skill <= ? " +
      "ORDER BY needed_skill ASC ");
    stmt.setString(1, jp_code);
    stmt.setString(2, k);
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  // need to figure out what Tu's issue was

  public ResultSet twenty(String jp_code) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "with jp_required_skills as ( " +
      "  select jp_code, skill_code " +
      "  from job_profile natural join requires " +
      "), " +
      "needed_skill_count as ( " +
      "  select per_id, " +
      "        (select count(skill_code) " +
      "          from (select skill_code " +
      "                from jp_required_skills R " +
      "                where R.jp_code = ? " +
      "                minus " +
      "                select skill_code " +
      "                from has H " +
      "                where H.per_id = P.per_id)) " +
      "          as needed_skill " +
      "  from person P " +
      "), " +
      "missing_k as ( " +
      "  select * " +
      "  from needed_skill_count " +
      "  where needed_skill <= 2 " +
      "  order by needed_skill asc " +
      "), " +
      "missing_skills as ( " +
      "  select per_id, skill_code " +
      "  from person natural join jp_required_skills " +
      "  where jp_code = ? " +
      "  minus " +
      "  select per_id, skill_code " +
      "  from person natural join has " +
      ") " +
      " " +
      "select skill_code, count(per_id) as num_people_missing " +
      "from missing_k natural join missing_skills " +
      "group by skill_code " +
      "order by num_people_missing desc ");
    stmt.setString(1, jp_code);
    stmt.setString(2, jp_code);
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet twentyone(String jp_code) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      " SELECT per_id, name  "   +
      " FROM person NATURAL JOIN worked NATURAL JOIN job NATURAL JOIN job_profile  "   +
      " WHERE jp_code = ?  ");
    stmt.setString(1, jp_code);
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet twentytwo(String jp_code) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      " WITH unemployed AS ( " +
      "   SELECT person.per_id, person.name " +
      "   FROM person LEFT OUTER JOIN works ON person.per_id = works.per_id " +
      "   WHERE job_code IS NULL " +
      " ) " +
      " SELECT per_id, name " +
      " FROM unemployed NATURAL JOIN worked NATURAL JOIN job NATURAL JOIN job_profile " +
      " WHERE jp_code = ? ");
    stmt.setString(1, jp_code);
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet twentythreea() throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "WITH employee_count as ( " +
      "  SELECT comp_id, COUNT (DISTINCT per_id) AS count_employees " +
      "  FROM works NATURAL JOIN job NATURAL JOIN company " +
      "  GROUP BY comp_id " +
      ") " +
      " " +
      "SELECT comp_id, count_employees " +
      "FROM employee_count " +
      "WHERE count_employees = (SELECT MAX(count_employees) " +
      "                         FROM employee_count) ");
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet twentythreeb() throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "WITH computed_pay AS ( " +
      "  SELECT comp_id, J.job_code, " +
      "    CASE " +
      "      WHEN J.pay_type = 'salary' THEN J.pay_rate " +
      "      WHEN J.pay_type = 'wage' THEN J.pay_rate * 1920 " +
      "      ELSE NULL " +
      "    END " +
      "    \"Total Pay\" " +
      "  FROM job J " +
      "), " +
      "company_by_pay AS ( " +
      "  SELECT comp_id, SUM(\"Total Pay\") AS total_pay " +
      "  FROM computed_pay " +
      "  GROUP BY comp_id " +
      ") " +
      " " +
      "SELECT comp_id, total_pay " +
      "FROM company_by_pay " +
      "WHERE total_pay = (SELECT MAX(total_pay) " +
      "                   FROM company_by_pay) ");
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet twentyfoura() throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "WITH employee_count as ( " +
      "  SELECT comp_id, COUNT (per_id) AS count_employees " +
      "  FROM works NATURAL JOIN job NATURAL JOIN company " +
      "  GROUP BY comp_id " +
      "), " +
      "business_sector AS( " +
      "  SELECT primary_sector, SUM(count_employees) as sector_employee_count " +
      "  FROM employee_count NATURAL JOIN company " +
      "  GROUP BY primary_sector " +
      ") " +
      "SELECT primary_sector, sector_employee_count " +
      "FROM business_sector " +
      "WHERE sector_employee_count = (SELECT MAX(sector_employee_count) " +
      "                               FROM business_sector) ");
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet twentyfourb() throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "WITH computed_pay AS ( " +
      "  SELECT comp_id, J.job_code, " +
      "    CASE " +
      "      WHEN J.pay_type = 'salary' THEN J.pay_rate " +
      "      WHEN J.pay_type = 'wage' THEN J.pay_rate * 1920 " +
      "      ELSE NULL " +
      "    END " +
      "    \"Total Pay\" " +
      "  FROM job J " +
      "), " +
      "company_by_pay AS ( " +
      "  SELECT comp_id, SUM(\"Total Pay\") AS total_pay " +
      "  FROM computed_pay " +
      "  GROUP BY comp_id " +
      "), " +
      "sector_by_pay AS( " +
      "  SELECT primary_sector, SUM(total_pay) as sector_pay " +
      "  FROM company_by_pay NATURAL JOIN company " +
      "  GROUP BY primary_sector " +
      ") " +
      " " +
      "SELECT primary_sector, sector_pay " +
      "FROM sector_by_pay " +
      "WHERE sector_pay = (SELECT MAX(sector_pay) " +
      "                    FROM sector_by_pay) ");
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet twentyfive() throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "WITH computed_pay AS ( " +
      "  SELECT J.job_code, " +
      "    CASE " +
      "      WHEN J.pay_type = 'salary' THEN J.pay_rate " +
      "      WHEN J.pay_type = 'wage' THEN J.pay_rate * 1920 " +
      "      ELSE NULL " +
      "    END " +
      "    \"Total Pay\" " +
      "  FROM job J " +
      "), " +
      "current_pay AS ( " +
      "  SELECT per_id, \"Total Pay\", primary_sector " +
      "  FROM works NATURAL JOIN job NATURAL JOIN company NATURAL JOIN computed_pay " +
      "), " +
      "old_pay as( " +
      "  SELECT per_id, \"Total Pay\", primary_sector " +
      "  FROM worked NATURAL JOIN job NATURAL JOIN company NATURAL JOIN computed_pay " +
      "), " +
      "num_increase as( " +
      "  SELECT primary_sector, COUNT(DISTINCT per_id) AS increase " +
      "  FROM current_pay C " +
      "  WHERE EXISTS (SELECT I.per_id " +
      "                FROM old_pay I " +
      "                WHERE C.per_id = I.per_id) " +
      "  AND \"Total Pay\" > (SELECT MAX(\"Total Pay\") " +
      "                     FROM old_pay O " +
      "                     WHERE C.per_id = O.per_id) " +
      "  GROUP BY primary_sector " +
      "), " +
      "num_decrease as( " +
      "  SELECT primary_sector, COUNT(DISTINCT per_id) AS decrease " +
      "  FROM current_pay C " +
      "  WHERE EXISTS (SELECT I.per_id " +
      "                FROM old_pay I " +
      "                WHERE C.per_id = I.per_id) " +
      "  AND \"Total Pay\" < (SELECT MAX(\"Total Pay\") " +
      "                     FROM old_pay O " +
      "                     WHERE C.per_id = O.per_id) " +
      "  GROUP BY primary_sector " +
      ") " +
      " " +
      "SELECT D.primary_sector, I.increase, D.decrease, " +
      "  CASE " +
      "      WHEN I.increase IS NULL THEN 1/D.decrease " +
      "      WHEN D.decrease IS NULL THEN I.increase/1 " +
      "      ELSE I.increase/D.decrease " +
      "   END " +
      "   AS total_ratio " +
      "FROM num_decrease D FULL JOIN num_increase I " +
      "ON D.primary_sector = I.primary_sector ");
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet twentysix() throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "WITH unfilled_jobs AS ( " +
      "  SELECT job.jp_code, COUNT(job.job_code) AS vacancies " +
      "  FROM job LEFT OUTER JOIN works " +
      "  ON job.job_code = works.job_code " +
      "  WHERE per_id IS NULL " +
      "  GROUP BY jp_code " +
      "), " +
      "unemployed AS ( " +
      "  SELECT person.per_id " +
      "  FROM person LEFT OUTER JOIN works " +
      "  ON person.per_id = works.per_id " +
      "  WHERE job_code IS NULL " +
      "), " +
      "qualified AS ( " +
      "  SELECT UJ.jp_code, COUNT(per_id) AS qualified_unemployed " +
      "  FROM unemployed P, unfilled_jobs UJ " +
      "  WHERE NOT EXISTS (SELECT skill_code " +
      "                FROM requires R " +
      "                WHERE R.jp_code = UJ.jp_code " +
      "                MINUS " +
      "                SELECT skill_code " +
      "                FROM has H " +
      "                WHERE H.per_id = P.per_id) " +
      "  AND NOT EXISTS (SELECT cer_code " +
      "             FROM requires_certificate RC " +
      "             WHERE RC.jp_code = UJ.jp_code " +
      "             MINUS " +
      "             SELECT cer_code " +
      "             FROM has_certificate HC " +
      "             WHERE HC.per_id = P.per_id) " +
      "  GROUP BY UJ.jp_code " +
      "), " +
      "difference AS ( " +
      "  SELECT U.jp_code, " +
      "    CASE " +
      "        WHEN Q.qualified_unemployed IS NOT NULL THEN U.vacancies - Q.qualified_unemployed " +
      "        ELSE U.vacancies " +
      "      END " +
      "      \"Difference\" " +
      "  FROM unfilled_jobs U LEFT JOIN qualified Q ON U.jp_code = Q.jp_code " +
      ") " +
      " " +
      "SELECT jp_code " +
      "FROM difference " +
      "WHERE \"Difference\" = (SELECT MAX(\"Difference\") " +
      "                      FROM difference) ");
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet twentysevena() throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "INSERT INTO CourseSet " +
      "SELECT CourseSet_seq.NEXTVAL, C1.c_code, C2.c_code, null, 2 " +
      "FROM Course C1, Course C2 " +
      "WHERE C1.c_code < C2.c_code "
    );
    stmt.executeQuery();

    stmt = conn.prepareStatement(
      "INSERT INTO CourseSet " +
      "SELECT CourseSet_seq.NEXTVAL, C1.c_code, C2.c_code, C3.c_code, 3 " +
      "FROM Course C1, Course C2, Course C3 " +
      "WHERE C1.c_code < C2.c_code AND C2.c_code < C3.c_code "
    );
    stmt.executeQuery();

    stmt = conn.prepareStatement(
      "WITH unfilled_jobs AS ( " +
      "  SELECT job.jp_code, COUNT(job.job_code) AS vacancies " +
      "  FROM job LEFT OUTER JOIN works " +
      "  ON job.job_code = works.job_code " +
      "  WHERE per_id IS NULL " +
      "  GROUP BY jp_code " +
      "), " +
      "unemployed AS ( " +
      "  SELECT person.per_id " +
      "  FROM person LEFT OUTER JOIN works " +
      "  ON person.per_id = works.per_id " +
      "  WHERE job_code IS NULL " +
      "), " +
      "qualified AS ( " +
      "  SELECT UJ.jp_code, COUNT(per_id) AS qualified_unemployed " +
      "  FROM unemployed P, unfilled_jobs UJ " +
      "  WHERE NOT EXISTS (SELECT skill_code " +
      "                FROM requires R " +
      "                WHERE R.jp_code = UJ.jp_code " +
      "                MINUS " +
      "                SELECT skill_code " +
      "                FROM has H " +
      "                WHERE H.per_id = P.per_id) " +
      "  AND NOT EXISTS (SELECT cer_code " +
      "             FROM requires_certificate RC " +
      "             WHERE RC.jp_code = UJ.jp_code " +
      "             MINUS " +
      "             SELECT cer_code " +
      "             FROM has_certificate HC " +
      "             WHERE HC.per_id = P.per_id) " +
      "  GROUP BY UJ.jp_code " +
      "), " +
      "difference AS ( " +
      "  SELECT U.jp_code, " +
      "    CASE " +
      "        WHEN Q.qualified_unemployed IS NOT NULL THEN U.vacancies - Q.qualified_unemployed " +
      "        ELSE U.vacancies " +
      "      END " +
      "      \"Difference\" " +
      "  FROM unfilled_jobs U LEFT JOIN qualified Q ON U.jp_code = Q.jp_code " +
      "), " +
      "jobs_lacking_qualified as ( " +
      "  SELECT jp_code " +
      "  FROM difference " +
      "  WHERE \"Difference\" = (SELECT MAX(\"Difference\") " +
      "                        FROM difference) " +
      "), " +
      "CourseSet_Skill(csetID, skill_code) AS ( " +
      "  SELECT csetID, skill_code " +
      "  FROM CourseSet CSet JOIN covers CS ON CSet.c_code1=CS.c_code " +
      "  UNION " +
      "  SELECT csetID, skill_code " +
      "  FROM CourseSet CSet JOIN covers CS ON CSet.c_code2=CS.c_code " +
      "  UNION " +
      "  SELECT csetID, skill_code " +
      "  FROM CourseSet CSet JOIN covers CS ON CSet.c_code3=CS.c_code " +
      "), " +
      "CourseSet_Certificate(csetID, cer_code) AS ( " +
      "  SELECT csetID, cer_code " +
      "  FROM CourseSet CSet JOIN gives_certificate CS ON CSet.c_code1=CS.c_code " +
      "  UNION " +
      "  SELECT csetID, cer_code " +
      "  FROM CourseSet CSet JOIN gives_certificate CS ON CSet.c_code2=CS.c_code " +
      "  UNION " +
      "  SELECT csetID, cer_code " +
      "  FROM CourseSet CSet JOIN gives_certificate CS ON CSet.c_code3=CS.c_code " +
      "), " +
      "missing_skills AS ( " +
      "  SELECT skill_code " +
      "  FROM jobs_lacking_qualified NATURAL JOIN job_profile NATURAL JOIN requires " +
      "), " +
      "missing_certificates AS ( " +
      "  SELECT cer_code " +
      "  FROM jobs_lacking_qualified NATURAL JOIN job_profile NATURAL JOIN requires_certificate " +
      "), " +
      "Cover_CSet(csetID, cs_size) AS ( " +
      "  SELECT csetID, cs_size " +
      "  FROM CourseSet CSet " +
      "  WHERE NOT EXISTS (SELECT skill_code " +
      "                    FROM missing_skills " +
      "                    MINUS " +
      "                    SELECT skill_code " +
      "                    FROM CourseSet_Skill CSSk " +
      "                    WHERE CSSk.csetID = CSet.csetID) " +
      "                    AND NOT EXISTS " +
      "                    (SELECT cer_code " +
      "                    FROM missing_certificates " +
      "                    MINUS " +
      "                    SELECT cer_code " +
      "                    FROM CourseSet_Certificate CSC " +
      "                    WHERE CSC.csetID = CSet.csetID) " +
      ") " +
      "SELECT DISTINCT c_code1, c_code2, c_code3 " +
      "FROM Cover_CSet NATURAL JOIN CourseSet " +
      "WHERE cs_size = (SELECT MIN(cs_size) FROM Cover_CSet) ");
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet twentysevenb() throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "WITH unfilled_jobs AS ( " +
      "  SELECT job.jp_code, COUNT(job.job_code) AS vacancies " +
      "  FROM job LEFT OUTER JOIN works " +
      "  ON job.job_code = works.job_code " +
      "  WHERE per_id IS NULL " +
      "  GROUP BY jp_code " +
      "), " +
      "unemployed AS ( " +
      "  SELECT person.per_id " +
      "  FROM person LEFT OUTER JOIN works " +
      "  ON person.per_id = works.per_id " +
      "  WHERE job_code IS NULL " +
      "), " +
      "qualified AS ( " +
      "  SELECT UJ.jp_code, COUNT(per_id) AS qualified_unemployed " +
      "  FROM unemployed P, unfilled_jobs UJ " +
      "  WHERE NOT EXISTS (SELECT skill_code " +
      "                FROM requires R " +
      "                WHERE R.jp_code = UJ.jp_code " +
      "                MINUS " +
      "                SELECT skill_code " +
      "                FROM has H " +
      "                WHERE H.per_id = P.per_id) " +
      "  AND NOT EXISTS (SELECT cer_code " +
      "             FROM requires_certificate RC " +
      "             WHERE RC.jp_code = UJ.jp_code " +
      "             MINUS " +
      "             SELECT cer_code " +
      "             FROM has_certificate HC " +
      "             WHERE HC.per_id = P.per_id) " +
      "  GROUP BY UJ.jp_code " +
      "), " +
      "difference AS ( " +
      "  SELECT U.jp_code, " +
      "    CASE " +
      "        WHEN Q.qualified_unemployed IS NOT NULL THEN U.vacancies - Q.qualified_unemployed " +
      "        ELSE U.vacancies " +
      "      END " +
      "      \"Difference\" " +
      "  FROM unfilled_jobs U LEFT JOIN qualified Q ON U.jp_code = Q.jp_code " +
      "), " +
      "jobs_lacking_qualified AS ( " +
      "  SELECT jp_code " +
      "  FROM difference " +
      "  WHERE \"Difference\" = (SELECT MAX(\"Difference\") " +
      "                        FROM difference) " +
      ") " +
      " " +
      "SELECT c_code " +
      "FROM jobs_lacking_qualified NATURAL JOIN requires NATURAL JOIN covers " +
      "UNION " +
      "SELECT c_code " +
      "FROM jobs_lacking_qualified NATURAL JOIN requires_certificate NATURAL JOIN gives_certificate ");
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

  public ResultSet twentyeight(String jp_code, String id) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(
      "WITH missing_skills AS ( " +
      "  SELECT skill_code " +
      "  FROM requires " +
      "  WHERE jp_code = ? " +
      "  MINUS " +
      "  SELECT skill_code " +
      "  FROM has " +
      "  WHERE per_id = ? " +
      "), " +
      "missing_certificates AS ( " +
      "  SELECT cer_code " +
      "  FROM requires_certificate " +
      "  WHERE jp_code = ? " +
      "  MINUS " +
      "  SELECT cer_code " +
      "  FROM has_certificate " +
      "  WHERE per_id = ? " +
      ") " +
      " " +
      "SELECT DISTINCT c_code FROM missing_skills NATURAL JOIN covers " +
      "UNION " +
      "SELECT DISTINCT c_code FROM missing_certificates NATURAL JOIN gives_certificate ");
    stmt.setString(1, jp_code);
    stmt.setString(2, id);
    stmt.setString(3, jp_code);
    stmt.setString(4, id);
    ResultSet rset = stmt.executeQuery();
    return rset;
  }

}
