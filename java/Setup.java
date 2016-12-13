import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Setup {
  private Connection conn;

  public Setup(Connection conn) {
    this.conn = conn;
  }

  public void clean() throws SQLException {
    PreparedStatement stmt;
    stmt = conn.prepareStatement("drop table gives_certificate "); stmt.executeQuery();
    stmt = conn.prepareStatement("drop table has_certificate "); stmt.executeQuery();
    stmt = conn.prepareStatement("drop table requires_certificate "); stmt.executeQuery();
    stmt = conn.prepareStatement("drop table worked "); stmt.executeQuery();
    stmt = conn.prepareStatement("drop table works "); stmt.executeQuery();
    stmt = conn.prepareStatement("drop table covers "); stmt.executeQuery();
    stmt = conn.prepareStatement("drop table requires "); stmt.executeQuery();
    stmt = conn.prepareStatement("drop table has "); stmt.executeQuery();
    stmt = conn.prepareStatement("drop table takes "); stmt.executeQuery();
    stmt = conn.prepareStatement("drop table certificate "); stmt.executeQuery();
    stmt = conn.prepareStatement("drop table tool "); stmt.executeQuery();
    stmt = conn.prepareStatement("drop table job "); stmt.executeQuery();
    stmt = conn.prepareStatement("drop table specialty "); stmt.executeQuery();
    stmt = conn.prepareStatement("drop table company_address "); stmt.executeQuery();
    stmt = conn.prepareStatement("drop table company "); stmt.executeQuery();
    stmt = conn.prepareStatement("drop table job_profile "); stmt.executeQuery();
    stmt = conn.prepareStatement("drop table section "); stmt.executeQuery();
    stmt = conn.prepareStatement("drop table course "); stmt.executeQuery();
    stmt = conn.prepareStatement("drop table skill "); stmt.executeQuery();
    stmt = conn.prepareStatement("drop table address "); stmt.executeQuery();
    stmt = conn.prepareStatement("drop table phone "); stmt.executeQuery();
    stmt = conn.prepareStatement("drop table person "); stmt.executeQuery();
    stmt = conn.prepareStatement("drop table CourseSet "); stmt.executeQuery();
    stmt = conn.prepareStatement("drop sequence CourseSet_seq "); stmt.executeQuery();
  }

  public void setup() throws SQLException {
    PreparedStatement stmt;
    stmt = conn.prepareStatement(
      "create table person " +
      "( " +
        "    per_id number not null, " +
        "    name varchar(50) not null, " +
        "    email varchar(50) not null, " +
        "    gender varchar(10) not null, " +
        "    primary key(per_id)" +
        " ) "
    );
    stmt.executeQuery();

    stmt = conn.prepareStatement(
      "create table phone " +
      "( " +
        "    per_id number not null, " +
        "    phone_num varchar(20) not null, " +
        "    phone_type varchar(20) not null, " +
        "    primary key (per_id,phone_num), " +
        "    foreign key (per_id) references person" +
        " ) "
    );
    stmt.executeQuery();

    stmt = conn.prepareStatement(
      "create table address " +
      "  ( " +
      "    per_id number not null, " +
      "    street varchar(50) not null, " +
      "    city varchar(50) not null, " +
      "    state varchar(30) not null, " +
      "    zip_code number(10,0) not null, " +
      "    primary key(per_id, street, city), " +
      "    foreign key(per_id) references person" +
      " ) "
    );
    stmt.executeQuery();

    stmt = conn.prepareStatement(
      "create table skill " +
      "  ( " +
      "    skill_code varchar(8) not null, " +
      "    title varchar(50) not null, " +
      "    description varchar(300) not null, " +
      "    skill_level varchar(10) " +
      "      check (skill_level in ('beginner', 'medium', 'advanced')), " +
      "    primary key(skill_code)" +
      " ) "
    );
    stmt.executeQuery();

    stmt = conn.prepareStatement(
      "create table course " +
      "   ( " +
      "    c_code number(6,0) not null, " +
      "    title varchar(50) not null, " +
      "    course_level varchar(10) " +
      "      check (course_level in ('beginner', 'medium', 'advanced')), " +
      "    description varchar(200) not null, " +
      "    status varchar(8) " +
      "      check (status in ('active', 'expired')), " +
      "    retail_price number(8,2) not null, " +
      "    primary key (c_code)" +
      " ) "
    );
    stmt.executeQuery();

    stmt = conn.prepareStatement(
      "   create table section " +
      "  ( " +
      "    c_code number(6,0) not null, " +
      "    sec_no number not null, " +
      "    year number(4,0) not null, " +
      "    complete_date date not null, " +
      "    offered_by varchar(50) not null, " +
      "    format varchar(50) " +
      "      check (format in ('classroom', 'online-sync', 'online-selfpaced', 'correspondence')), " +
      "    price number(8,2) not null, " +
      "    primary key (c_code,sec_no,year), " +
      "    foreign key (c_code) references course" +
      " ) "
    );
    stmt.executeQuery();

    stmt = conn.prepareStatement(
      "create table job_profile " +
      "  ( " +
      "    jp_code varchar(8) not null, " +
      "    title varchar(50) not null, " +
      "    description varchar(300) not null, " +
      "    avg_pay number(10,2) not null, " +
      "    primary key (jp_code) " +
      "   ) "
    );
    stmt.executeQuery();

    stmt = conn.prepareStatement(
      "create table company " +
      "  ( " +
      "    comp_id number not null, " +
      "    comp_name varchar(50) not null, " +
      "    primary_sector varchar(50) not null, " +
      "    website varchar(50) not null, " +
      "    primary key (comp_id) " +
      "  ) "
    );
    stmt.executeQuery();

    stmt = conn.prepareStatement(
      "create table company_address " +
      "  ( " +
      "    comp_id number not null, " +
      "    street varchar(50) not null, " +
      "    city varchar(50) not null, " +
      "    state varchar(50) not null, " +
      "    zip_code number(10,0) not null, " +
      "    primary key (comp_id, street, city, state), " +
      "    foreign key (comp_id) references company " +
      "  ) "
    );
    stmt.executeQuery();

    stmt = conn.prepareStatement(
      "create table specialty " +
      "  ( " +
      "    comp_id number not null, " +
      "    specialty_type varchar(50) " +
      "      check (specialty_type in ('construction', 'logistics service', 'computer hardware', 'software', 'electronics', 'basic materials', 'IT consulting', 'transportation')), " +
      "    primary key (comp_id, specialty_type), " +
      "    foreign key (comp_id) references company " +
      "   ) "
    );
    stmt.executeQuery();

    stmt = conn.prepareStatement(
      "create table job " +
      "  ( " +
      "    job_code number not null, " +
      "    comp_id number not null, " +
      "    jp_code varchar(8) not null, " +
      "    type varchar(10) " +
      "      check (type in ('full-time','part-time')), " +
      "    pay_rate number(8,2) not null, " +
      "    pay_type varchar(10) " +
      "      check (pay_type in ('wage','salary')), " +
      "    primary key(job_code), " +
      "    foreign key(comp_id) references company, " +
      "    foreign key(jp_code) references job_profile " +
      "  ) "
    );
    stmt.executeQuery();


    stmt = conn.prepareStatement(
      "create table tool " +
      "  ( " +
      "    t_code varchar(8) not null, " +
      "    t_name varchar(50) not null, " +
      "    primary key (t_code) " +
      "   ) "
    );
    stmt.executeQuery();

    stmt = conn.prepareStatement(
      "create table certificate " +
      "  ( " +
      "    cer_code varchar(8) not null, " +
      "    cer_title varchar(50) not null, " +
      "    cer_description varchar(200) not null, " +
      "    t_code varchar(8) not null, " +
      "    expire_date date not null, " +
      "    comp_id number not null, " +
      "    primary key (cer_code), " +
      "    foreign key (t_code) references tool, " +
      "    foreign key (comp_id) references company " +
      "  ) "
    );
    stmt.executeQuery();

    stmt = conn.prepareStatement(
      "create table takes " +
      "  ( " +
      "    per_id number not null, " +
      "    c_code number(6,0) not null, " +
      "    sec_no number not null, " +
      "    year number(4,0) not null, " +
      "    primary key(per_id,c_code,sec_no), " +
      "    foreign key(per_id) references person, " +
      "    foreign key(c_code,sec_no,year) references section " +
      "   ) "
    );
    stmt.executeQuery();

    stmt = conn.prepareStatement(
      "create table has " +
      "  ( " +
      "    per_id number not null, " +
      "    skill_code varchar(8) not null, " +
      "    primary key(per_id,skill_code), " +
      "    foreign key(per_id) references person, " +
      "    foreign key(skill_code) references skill " +
      "   ) "
    );
    stmt.executeQuery();

    stmt = conn.prepareStatement(
      "create table requires " +
      "  ( " +
      "    jp_code varchar(8) not null, " +
      "    skill_code varchar(8) not null, " +
      "    primary key(jp_code,skill_code), " +
      "    foreign key(jp_code) references job_profile, " +
      "    foreign key(skill_code) references skill " +
      "   ) "
    );
    stmt.executeQuery();

    stmt = conn.prepareStatement(
      "create table covers " +
      "  ( " +
      "    c_code number(6,0) not null, " +
      "    skill_code varchar(8) not null, " +
      "    primary key(c_code,skill_code), " +
      "    foreign key(c_code) references course, " +
      "    foreign key(skill_code) references skill " +
      "   )  "
    );
    stmt.executeQuery();

    stmt = conn.prepareStatement(
      "create table works " +
      "  ( " +
      "    per_id number not null, " +
      "    job_code number not null, " +
      "    primary key (per_id,job_code), " +
      "    foreign key (per_id) references person, " +
      "    foreign key (job_code) references job " +
      "  ) "
    );
    stmt.executeQuery();

    stmt = conn.prepareStatement(
      "create table worked " +
      "  ( " +
      "    per_id number not null, " +
      "    job_code number not null, " +
      "    primary key (per_id,job_code), " +
      "    foreign key (per_id) references person, " +
      "    foreign key (job_code) references job " +
      "   ) "
    );
    stmt.executeQuery();

    stmt = conn.prepareStatement(
      "create table requires_certificate" +
      "  ( " +
      "    jp_code varchar(8) not null, " +
      "    cer_code varchar(8) not null, " +
      "    primary key (jp_code,cer_code), " +
      "    foreign key (jp_code) references job_profile, " +
      "    foreign key (cer_code) references certificate " +
      "   ) "
    );
    stmt.executeQuery();

    stmt = conn.prepareStatement(
      "create table has_certificate" +
      "  ( " +
      "    per_id number not null, " +
      "    cer_code varchar(8) not null, " +
      "    primary key (per_id,cer_code), " +
      "    foreign key (per_id) references person, " +
      "    foreign key (cer_code) references certificate " +
      "   ) "
    );
    stmt.executeQuery();

    stmt = conn.prepareStatement(
      "create table gives_certificate" +
      "  ( " +
      "    c_code number(6,0) not null, " +
      "    cer_code varchar(8) not null, " +
      "    primary key (c_code,cer_code), " +
      "    foreign key (c_code) references course, " +
      "    foreign key (cer_code) references certificate " +
      "   ) "
    );
    stmt.executeQuery();

    stmt = conn.prepareStatement(
      "create table CourseSet " +
      "( " +
      "  csetID number(8,0), " +
      "  c_code1 number(6,0), " +
      "  c_code2 number(6,0), " +
      "  c_code3 number(6,0), " +
      "  cs_size number(8,0), " +
      "  primary key(csetID) " +
      ") "
    );
    stmt.executeQuery();

    stmt = conn.prepareStatement(
      "CREATE SEQUENCE CourseSet_seq " +
      "START WITH 1 " +
      "INCREMENT BY 1 " +
      "MAXVALUE 999999 " +
      "NOCYCLE "
    );
    stmt.executeQuery();
  }

  public void seed() throws SQLException {
    PreparedStatement stmt;
    stmt = conn.prepareStatement("INSERT INTO person VALUES (1, 'August Marsalis', 'foo@bar.com', 'M') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO person VALUES (2, 'Daniel DeKerlegand', 'abc@xyz.com', 'M') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO person VALUES (3, 'Shengru Tu', 'nothing@something.com', 'M') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO person VALUES (4, 'Captain Planet', 'student@uno.edu', 'F') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO person VALUES (5, 'Dorothy Parker', 'test@startup.co', 'M') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO person VALUES (6, 'William Butler','personsix@gmail.com', 'M') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO person VALUES (7,'Giacomo Mckee','metus.In@urna.ca','F') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO person VALUES (8,'Bo Buckley','eget.volutpat@egestasnuncsed.org','F') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO person VALUES (9,'Graiden Lamb','a.malesuada@Uttincidunt.net','F') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO person VALUES (10,'Juliet Benson','vel@nisl.com','F') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO person VALUES (11,'Shad Booth','Etiam.vestibulum.massa@VivamusrhoncusDonec.com','F') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO person VALUES (12,'Marsden James','vitae.dolor.Donec@nibhsitamet.co.uk','M') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO person VALUES (13,'Nicholas Shannon','pharetra.ut@elementum.org','F') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO person VALUES (14,'Destiny George','Aliquam.tincidunt.nunc@egestasurna.co.uk','F') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO person VALUES (15,'Moana Mcconnell','enim.Nunc.ut@consectetuer.net','F') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO person VALUES (16,'Liberty Bauer','ipsum.Curabitur.consequat@sedest.co.uk','F') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO person VALUES (17,'Mary Green','metus.eu@neque.com','M') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO person VALUES (18,'Charissa Moreno','at@amet.ca','M') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO person VALUES (19,'Cecilia Williams','ligula.Donec@disparturientmontes.org','F') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO person VALUES (20,'Ann Benton','Etiam.ligula@sociosqu.edu','M') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO person VALUES (21,'Ocean Bender','Sed.nulla.ante@sed.com','F') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO person VALUES (22,'Emerson Noble','lobortis.Class.aptent@ut.edu','M') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO person VALUES (23,'Shay Huber','eget.volutpat.ornare@aauctor.ca','M') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO person VALUES (24,'Jayme Cooke','eros@egestas.com','F') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO person VALUES (25,'Savannah Pittman','Donec.sollicitudin@elitAliquamauctor.edu','M') "); stmt.executeQuery();

    stmt = conn.prepareStatement("INSERT INTO phone VALUES (1, '337-123-4567', 'cell') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO phone VALUES (2, '504-230-1255', 'home') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO phone VALUES (3, '504-130-4020', 'cell') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO phone VALUES (4, '225-522-9534', 'cell') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO phone VALUES (5, '337-153-1962', 'home') "); stmt.executeQuery();

    stmt = conn.prepareStatement("INSERT INTO address VALUES (1, '105 Mulberry Lane', 'Lafayette', 'Louisiana', 70508) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO address VALUES (2, '204 Broad St', 'New Orleans', 'Louisiana', 70001) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO address VALUES (3, '1043 Dryades St', 'New Orleans', 'Louisiana', 70003) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO address VALUES (4, '604 Manhattan Blvd', 'Baton Rouge', 'Louisiana', 70705) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO address VALUES (5, '609 Smith St', 'Lake Charles', 'Louisiana', 72401) "); stmt.executeQuery();

    stmt = conn.prepareStatement("INSERT INTO skill VALUES ('OOP100', 'Object Oriented Programming', 'Has knowledge of basic object oriented programming principles', 'beginner') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO skill VALUES ('GD200', 'Game Design', 'Has knowledge of game level design and basic physics engine development', 'beginner') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO skill VALUES ('ROB304', 'Robotics Programming', 'Has medium level knowledge of robotics programming', 'medium') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO skill VALUES ('IMG420', 'Image Processing', 'Has an advanced level of image processing and image processing algorithms', 'advanced') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO skill VALUES ('DBA500', 'Advanced Databases', 'Has a strong knowledge of relational database principles and design', 'advanced') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO skill VALUES ('ER620', 'ER Diagrams', 'Has knowledge of developing complex ER diagrams', 'advanced') "); stmt.executeQuery();

    stmt = conn.prepareStatement("INSERT INTO course VALUES (1583, 'Introduction to Comp Sci', 'beginner', 'This course covers the basics of procedural and object oriented programming', 'active','300.00') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO course VALUES (2559, 'Introduction to Game Design', 'beginner', 'This course introduces programmers to the basics of game design and development', 'expired','150.00') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO course VALUES (3201, 'Advanced Robotics', 'advanced', 'This course covers advanced topics in robotics programming with a heavy emphasis on functional languages', 'active','600.00') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO course VALUES (5002, 'Advanced Image Processing', 'advanced', 'This course covers advanced image processing and algorithm design', 'expired','350.00') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO course VALUES (4125, 'Intermediate Database Concepts', 'medium', 'This course covers database query language principles and other topics like ER design', 'active','300.00') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO course VALUES (6990, 'Design Patterns', 'advanced', 'This course covers advanced design patterns', 'active','800.00') "); stmt.executeQuery();

    stmt = conn.prepareStatement("INSERT INTO section VALUES (1583, 001, 2016, TO_DATE('2016-DEC-6 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 'UNO', 'classroom', 500.00) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO section VALUES (1583, 002, 2017, TO_DATE('2017-AUG-8 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 'University of Phoenix', 'online-sync', 800.00) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO section VALUES (2559, 001, 2017, TO_DATE('2017-AUG-30 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 'Tulane', 'online-sync', 2000.00) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO section VALUES (3201, 002, 2016, TO_DATE('2016-SEP-19 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 'Tulane', 'classroom', 2000.00) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO section VALUES (5002, 001, 2016, TO_DATE('2016-DEC-22 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 'Xavier', 'correspondence', 950.00) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO section VALUES (5002, 002, 2017, TO_DATE('2017-MAR-18 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 'Xavier', 'online-sync', 950.00) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO section VALUES (5002, 003, 2017, TO_DATE('2017-MAY-2 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 'UNO', 'classroom', 500.00) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO section VALUES (4125, 001, 2016, TO_DATE('2014-NOV-9 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 'Dillard', 'online-selfpaced', 800.00) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO section VALUES (4125, 002, 2016, TO_DATE('2014-NOV-12 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 'Dillard', 'classroom', 800.00) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO section VALUES (4125, 003, 2017, TO_DATE('2017-NOV-12 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 'Tulane', 'classroom', 800.00) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO section VALUES (6990, 001, 2016, TO_DATE('2016-NOV-12 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 'Tulane', 'classroom', 1000.00) "); stmt.executeQuery();

    stmt = conn.prepareStatement("INSERT INTO job_profile VALUES ('AB001', 'Web Dev', 'Develop websites', 64970.00) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job_profile VALUES ('BC002', 'Software Dev', 'Develop software', 100690.00) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job_profile VALUES ('DE003', 'Network Admin', 'Develop and monitor networks', 77810.00) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job_profile VALUES ('FG004', 'Security Analyst', 'Protect information', 90120.00) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job_profile VALUES ('HI005', 'Database Admin', 'Use Databases', 81710.00) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job_profile VALUES ('JK006', 'Programmer', 'Write programs', 79530.00) "); stmt.executeQuery();

    stmt = conn.prepareStatement("INSERT INTO company VALUES (13579, 'SkyyQuad Productions', 'Power', 'www.foo.com') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO company VALUES (24680,'Google', 'Technology', 'www.google.com') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO company VALUES (34679, 'Amazon','Oil and Gas', 'www.amazon.com') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO company VALUES (46790, 'Oracle','Renewable Energy', 'www.oracle.com') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO company VALUES (57698,'Microsoft', 'Power', 'www.microsoft.com') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO company VALUES (22491, 'Lucid', 'Technology', 'www.lucid.com') "); stmt.executeQuery();

    stmt = conn.prepareStatement("INSERT INTO company_address VALUES (13579,'120 Smith Ave', 'Baton Rouge', 'LA', 70203) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO company_address VALUES (24680,'302 Broad St', 'New Orleans', 'LA', 70003) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO company_address VALUES (34679,'430 March Lane', 'Shreveport', 'LA', 70353) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO company_address VALUES (46790,'100 Robert Cove', 'Lafayette', 'LA', 70001) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO company_address VALUES (57698,'6000 Rampart St', 'New Orleans', 'LA', 70005) "); stmt.executeQuery();

    stmt = conn.prepareStatement("INSERT INTO specialty VALUES(13579,'construction') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO specialty VALUES(24680,'logistics service') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO specialty VALUES(34679,'computer hardware') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO specialty VALUES(46790,'electronics') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO specialty VALUES(57698, 'IT consulting') "); stmt.executeQuery();

    stmt = conn.prepareStatement("INSERT INTO job VALUES (1, 13579, 'AB001', 'part-time', 20.00,'wage') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job VALUES (2, 24680, 'BC002', 'full-time', 70000.00,'salary') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job VALUES (3, 34679, 'DE003', 'part-time', 50000.00,'salary') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job VALUES (4, 46790, 'FG004', 'part-time', 30.00,'wage') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job VALUES (5, 57698, 'HI005', 'full-time', 100000.00,'salary') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job VALUES (6, 22491, 'BC002', 'full-time', 65000, 'salary') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job VALUES (7, 22491, 'FG004', 'part-time', 40.00, 'wage') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job VALUES (8, 24680, 'BC002', 'full-time', 100000.00,'salary') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job VALUES (9, 13579, 'HI005', 'full-time', 90000, 'salary') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job VALUES (10, 13579, 'AB001', 'part-time', 20.00,'wage') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job VALUES (11, 24680, 'BC002', 'full-time', 50000.00,'salary') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job VALUES (12, 34679, 'DE003', 'part-time', 50000.00,'salary') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job VALUES (13, 46790, 'FG004', 'part-time', 30.00,'wage') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job VALUES (14, 57698, 'HI005', 'full-time', 80000.00,'salary') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job VALUES (15, 22491, 'BC002', 'full-time', 45000, 'salary') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job VALUES (16, 22491, 'FG004', 'part-time', 20.00, 'wage') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job VALUES (17, 24680, 'BC002', 'full-time', 50000.00,'salary') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job VALUES (18, 13579, 'HI005', 'full-time', 150000, 'salary') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job VALUES (19, 13579, 'AB001', 'part-time', 25.00,'wage') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job VALUES (20, 24680, 'BC002', 'full-time', 78000.00,'salary') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job VALUES (21, 34679, 'DE003', 'part-time', 200000.00,'salary') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job VALUES (22, 46790, 'FG004', 'part-time', 50.00,'wage') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job VALUES (23, 57698, 'HI005', 'full-time', 120000.00,'salary') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job VALUES (24, 22491, 'BC002', 'full-time', 35000, 'salary') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job VALUES (25, 22491, 'FG004', 'part-time', 15.00, 'wage') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job VALUES (26, 24680, 'BC002', 'full-time', 66000.00,'salary') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO job VALUES (27, 13579, 'HI005', 'full-time', 45000, 'salary') "); stmt.executeQuery();

    stmt = conn.prepareStatement("INSERT INTO tool VALUES ('243', 'Oracle Database') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO tool VALUES ('4', 'Java') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO tool VALUES ('230', 'IDAPro') "); stmt.executeQuery();

    stmt = conn.prepareStatement("INSERT INTO certificate VALUES ('1', 'Oracle Database Developer I', 'Able to use Oracle databases.', '243', TO_DATE('2017-DEC-6 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 22491) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO certificate VALUES ('2', 'Oracle Database Developer II', 'Able to use Oracle databases.', '243', TO_DATE('2017-DEC-6 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 22491) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO certificate VALUES ('3', 'Reverse engineering with IDAPro', 'Able to use IDAPro.', '230', TO_DATE('2018-DEC-6 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 22491) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO certificate VALUES ('4', 'Java Mastery', 'Strong Java skills.', '4', TO_DATE('2019-DEC-6 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 22491) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO certificate VALUES ('5', 'Design Patterns', 'Advanced level design patterns.', '4', TO_DATE('2019-DEC-6 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 22491) "); stmt.executeQuery();

    stmt = conn.prepareStatement("INSERT INTO gives_certificate VALUES (1583, '4') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO gives_certificate VALUES (4125, '1') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO gives_certificate VALUES (4125, '2') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO gives_certificate VALUES (6990, '5') "); stmt.executeQuery();

    stmt = conn.prepareStatement("INSERT INTO requires_certificate VALUES ('HI005', '1') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO requires_certificate VALUES ('HI005', '2') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO requires_certificate VALUES ('JK006', '4') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO requires_certificate VALUES ('FG004', '3') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO requires_certificate VALUES ('BC002', '5') "); stmt.executeQuery();

    stmt = conn.prepareStatement("INSERT INTO has_certificate VALUES ('3', '1') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO has_certificate VALUES ('3', '2') "); stmt.executeQuery();

    stmt = conn.prepareStatement("INSERT INTO takes VALUES (1, 1583, 001, 2016) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO takes VALUES (4, 1583, 001, 2016) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO takes VALUES (2, 5002, 001, 2016) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO takes VALUES (3, 4125, 001, 2016) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO takes VALUES (4, 4125, 003, 2017) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO takes VALUES (5, 2559, 001, 2017) "); stmt.executeQuery();

    stmt = conn.prepareStatement("INSERT INTO requires VALUES('AB001','OOP100') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO requires VALUES('BC002','OOP100') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO requires VALUES('DE003','OOP100') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO requires VALUES('FG004','OOP100') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO requires VALUES('HI005','DBA500') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO requires VALUES('HI005','ER620') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO requires VALUES('JK006','OOP100') "); stmt.executeQuery();

    stmt = conn.prepareStatement("INSERT INTO covers VALUES(1583,'OOP100') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO covers VALUES(2559,'GD200') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO covers VALUES(3201,'ROB304') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO covers VALUES(5002,'IMG420') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO covers VALUES(4125,'DBA500') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO covers VALUES(4125,'ER620') "); stmt.executeQuery();

    stmt = conn.prepareStatement("INSERT INTO has VALUES(1, 'OOP100') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO has VALUES(2, 'OOP100') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO has VALUES(3, 'ER620') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO has VALUES (3, 'DBA500') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO has VALUES(4, 'OOP100') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO has VALUES(4, 'IMG420') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO has VALUES(4, 'DBA500') "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO has VALUES(5, 'GD200') "); stmt.executeQuery();

    stmt = conn.prepareStatement("INSERT INTO worked VALUES(1, 1) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO worked VALUES(2, 2) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO worked VALUES(3, 3) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO worked VALUES(4, 4) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO worked VALUES(5, 5) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO worked VALUES(6, 6) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO worked VALUES (7,7) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO worked VALUES (8,8) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO worked VALUES (12,12) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO worked VALUES (13,13) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO worked VALUES (15,15) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO worked VALUES (16,16) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO worked VALUES (17,17) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO worked VALUES (18,18) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO worked VALUES (19,27) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO worked VALUES (20,20) "); stmt.executeQuery();

    stmt = conn.prepareStatement("INSERT INTO works VALUES (17,20) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO works VALUES (2,27) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO works VALUES (15,17) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO works VALUES (4,1) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO works VALUES (5,22) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO works VALUES (25,3) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO works VALUES (24,12) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO works VALUES (8,13) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO works VALUES (9,24) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO works VALUES (10,20) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO works VALUES (11,21) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO works VALUES (3,2) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO works VALUES (6,7) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO works VALUES (14,9) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO works VALUES (7,17) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO works VALUES (19,26) "); stmt.executeQuery();
    stmt = conn.prepareStatement("INSERT INTO works VALUES (20,25) "); stmt.executeQuery();
  }

}
