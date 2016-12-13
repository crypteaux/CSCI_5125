INSERT INTO person VALUES (1, 'August Marsalis', 'foo@bar.com', 'M');
INSERT INTO person VALUES (2, 'Daniel DeKerlegand', 'abc@xyz.com', 'M');
INSERT INTO person VALUES (3, 'Shengru Tu', 'nothing@something.com', 'M');
INSERT INTO person VALUES (4, 'Captain Planet', 'student@uno.edu', 'F');
INSERT INTO person VALUES (5, 'Dorothy Parker', 'test@startup.co', 'M');
INSERT INTO person VALUES (6, 'William Butler','personsix@gmail.com', 'M');
INSERT INTO person VALUES (7,'Giacomo Mckee','metus.In@urna.ca','F');
INSERT INTO person VALUES (8,'Bo Buckley','eget.volutpat@egestasnuncsed.org','F');
INSERT INTO person VALUES (9,'Graiden Lamb','a.malesuada@Uttincidunt.net','F');
INSERT INTO person VALUES (10,'Juliet Benson','vel@nisl.com','F');
INSERT INTO person VALUES (11,'Shad Booth','Etiam.vestibulum.massa@VivamusrhoncusDonec.com','F');
INSERT INTO person VALUES (12,'Marsden James','vitae.dolor.Donec@nibhsitamet.co.uk','M');
INSERT INTO person VALUES (13,'Nicholas Shannon','pharetra.ut@elementum.org','F');
INSERT INTO person VALUES (14,'Destiny George','Aliquam.tincidunt.nunc@egestasurna.co.uk','F');
INSERT INTO person VALUES (15,'Moana Mcconnell','enim.Nunc.ut@consectetuer.net','F');
INSERT INTO person VALUES (16,'Liberty Bauer','ipsum.Curabitur.consequat@sedest.co.uk','F');
INSERT INTO person VALUES (17,'Mary Green','metus.eu@neque.com','M');
INSERT INTO person VALUES (18,'Charissa Moreno','at@amet.ca','M');
INSERT INTO person VALUES (19,'Cecilia Williams','ligula.Donec@disparturientmontes.org','F');
INSERT INTO person VALUES (20,'Ann Benton','Etiam.ligula@sociosqu.edu','M');
INSERT INTO person VALUES (21,'Ocean Bender','Sed.nulla.ante@sed.com','F');
INSERT INTO person VALUES (22,'Emerson Noble','lobortis.Class.aptent@ut.edu','M');
INSERT INTO person VALUES (23,'Shay Huber','eget.volutpat.ornare@aauctor.ca','M');
INSERT INTO person VALUES (24,'Jayme Cooke','eros@egestas.com','F');
INSERT INTO person VALUES (25,'Savannah Pittman','Donec.sollicitudin@elitAliquamauctor.edu','M');

INSERT INTO phone VALUES (1, '337-123-4567', 'cell');
INSERT INTO phone VALUES (2, '504-230-1255', 'home');
INSERT INTO phone VALUES (3, '504-130-4020', 'cell');
INSERT INTO phone VALUES (4, '225-522-9534', 'cell');
INSERT INTO phone VALUES (5, '337-153-1962', 'home');

INSERT INTO address VALUES (1, '105 Mulberry Lane', 'Lafayette', 'Louisiana', 70508);
INSERT INTO address VALUES (2, '204 Broad St', 'New Orleans', 'Louisiana', 70001);
INSERT INTO address VALUES (3, '1043 Dryades St', 'New Orleans', 'Louisiana', 70003);
INSERT INTO address VALUES (4, '604 Manhattan Blvd', 'Baton Rouge', 'Louisiana', 70705);
INSERT INTO address VALUES (5, '609 Smith St', 'Lake Charles', 'Louisiana', 72401);

INSERT INTO skill VALUES ('OOP100', 'Object Oriented Programming', 'Has knowledge of basic object oriented programming principles', 'beginner');
INSERT INTO skill VALUES ('GD200', 'Game Design', 'Has knowledge of game level design and basic physics engine development', 'beginner');
INSERT INTO skill VALUES ('ROB304', 'Robotics Programming', 'Has medium level knowledge of robotics programming', 'medium');
INSERT INTO skill VALUES ('IMG420', 'Image Processing', 'Has an advanced level of image processing and image processing algorithms', 'advanced');
INSERT INTO skill VALUES ('DBA500', 'Advanced Databases', 'Has a strong knowledge of relational database principles and design', 'advanced');
INSERT INTO skill VALUES ('ER620', 'ER Diagrams', 'Has knowledge of developing complex ER diagrams', 'advanced');

INSERT INTO course VALUES ('CS-101', 'Introduction to Comp Sci', 'beginner', 'This course covers the basics of procedural and object oriented programming', 'active','300.00');
INSERT INTO course VALUES ('CS-190', 'Introduction to Game Design', 'beginner', 'This course introduces programmers to the basics of game design and development', 'expired','150.00');
INSERT INTO course VALUES ('CS-315', 'Advanced Robotics', 'advanced', 'This course covers advanced topics in robotics programming with a heavy emphasis on functional languages', 'active','600.00');
INSERT INTO course VALUES ('CS-319', 'Advanced Image Processing', 'advanced', 'This course covers advanced image processing and algorithm design', 'expired','350.00');
INSERT INTO course VALUES ('CS-347', 'Intermediate Database Concepts', 'medium', 'This course covers database query language principles and other topics like ER design', 'active','300.00');
INSERT INTO course VALUES ('EE-181', 'Introduction to Digital Systems', 'beginner', 'This course introduces students to basic digital system engineering', 'expired','400.00');
INSERT INTO course VALUES ('FIN-201', 'Principles of Investment Banking', 'medium', 'This course covers intermediate concepts of investment banking with a heavy emphasis on formulas', 'active','800.00');
INSERT INTO course VALUES ('CS-405', 'Design Patterns', 'advanced', 'This course covers advanced design patterns', 'active','800.00');

INSERT INTO section VALUES ('EE-181', 1, 2016, TO_DATE('2016-DEC-6 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 'UNO', 'classroom', 500.00);
INSERT INTO section VALUES ('CS-315', 1, 2017, TO_DATE('2017-AUG-8 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 'University of Phoenix', 'online-sync', 800.00);
INSERT INTO section VALUES ('CS-101', 1, 2017, TO_DATE('2017-AUG-30 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 'Tulane', 'online-sync', 2000.00);
INSERT INTO section VALUES ('CS-101', 2, 2016, TO_DATE('2016-SEP-19 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 'Tulane', 'classroom', 2000.00);
INSERT INTO section VALUES ('CS-190', 1, 2016, TO_DATE('2016-DEC-22 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 'Xavier', 'correspondence', 950.00);
INSERT INTO section VALUES ('CS-190', 2, 2017, TO_DATE('2017-MAR-18 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 'Xavier', 'online-sync', 950.00);
INSERT INTO section VALUES ('CS-315', 3, 2017, TO_DATE('2017-MAY-2 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 'UNO', 'classroom', 500.00);
INSERT INTO section VALUES ('CS-319', 1, 2016, TO_DATE('2014-NOV-9 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 'Dillard', 'online-selfpaced', 800.00);
INSERT INTO section VALUES ('CS-319', 2, 2016, TO_DATE('2014-NOV-12 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 'Dillard', 'classroom', 800.00);
INSERT INTO section VALUES ('CS-347', 1, 2017, TO_DATE('2017-NOV-12 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 'Tulane', 'classroom', 800.00);
INSERT INTO section VALUES ('CS-347', 2, 2016, TO_DATE('2016-NOV-12 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 'Tulane', 'classroom', 1000.00);

INSERT INTO job_profile VALUES ('AB001', 'Web Dev', 'Develop websites', 64970.00);
INSERT INTO job_profile VALUES ('BC002', 'Software Dev', 'Develop software', 100690.00);
INSERT INTO job_profile VALUES ('DE003', 'Network Admin', 'Develop and monitor networks', 77810.00);
INSERT INTO job_profile VALUES ('FG004', 'Security Analyst', 'Protect information', 90120.00);
INSERT INTO job_profile VALUES ('HI005', 'Database Admin', 'Use Databases', 81710.00);
INSERT INTO job_profile VALUES ('JK006', 'Programmer', 'Write programs', 79530.00);

INSERT INTO company VALUES (13579, 'SkyyQuad Productions', 'Power', 'www.foo.com');
INSERT INTO company VALUES (24680,'Google', 'Technology', 'www.google.com');
INSERT INTO company VALUES (34679, 'Amazon','Oil and Gas', 'www.amazon.com');
INSERT INTO company VALUES (46790, 'Oracle','Renewable Energy', 'www.oracle.com');
INSERT INTO company VALUES (57698,'Microsoft', 'Power', 'www.microsoft.com');
INSERT INTO company VALUES (22491, 'Lucid', 'Technology', 'www.lucid.com');

INSERT INTO company_address VALUES (13579,'120 Smith Ave', 'Baton Rouge', 'LA', 70203);
INSERT INTO company_address VALUES (24680,'302 Broad St', 'New Orleans', 'LA', 70003);
INSERT INTO company_address VALUES (34679,'430 March Lane', 'Shreveport', 'LA', 70353);
INSERT INTO company_address VALUES (46790,'100 Robert Cove', 'Lafayette', 'LA', 70001);
INSERT INTO company_address VALUES (57698,'6000 Rampart St', 'New Orleans', 'LA', 70005);

INSERT INTO specialty VALUES(13579,'construction');
INSERT INTO specialty VALUES(24680,'logistics service');
INSERT INTO specialty VALUES(34679,'computer hardware');
INSERT INTO specialty VALUES(46790,'electronics');
INSERT INTO specialty VALUES(57698, 'IT consulting');

INSERT INTO job VALUES (1, 13579, 'AB001', 'part-time', 20.00,'wage');
INSERT INTO job VALUES (2, 24680, 'BC002', 'full-time', 70000.00,'salary');
INSERT INTO job VALUES (3, 34679, 'DE003', 'part-time', 50000.00,'salary');
INSERT INTO job VALUES (4, 46790, 'FG004', 'part-time', 30.00,'wage');
INSERT INTO job VALUES (5, 57698, 'HI005', 'full-time', 100000.00,'salary');
INSERT INTO job VALUES (6, 22491, 'BC002', 'full-time', 65000, 'salary');
INSERT INTO job VALUES (7, 22491, 'FG004', 'part-time', 40.00, 'wage');
INSERT INTO job VALUES (8, 24680, 'BC002', 'full-time', 100000.00,'salary');
INSERT INTO job VALUES (9, 13579, 'HI005', 'full-time', 90000, 'salary');
INSERT INTO job VALUES (10, 13579, 'AB001', 'part-time', 20.00,'wage');
INSERT INTO job VALUES (11, 24680, 'BC002', 'full-time', 50000.00,'salary');
INSERT INTO job VALUES (12, 34679, 'DE003', 'part-time', 50000.00,'salary');
INSERT INTO job VALUES (13, 46790, 'FG004', 'part-time', 30.00,'wage');
INSERT INTO job VALUES (14, 57698, 'HI005', 'full-time', 80000.00,'salary');
INSERT INTO job VALUES (15, 22491, 'BC002', 'full-time', 45000, 'salary');
INSERT INTO job VALUES (16, 22491, 'FG004', 'part-time', 20.00, 'wage');
INSERT INTO job VALUES (17, 24680, 'BC002', 'full-time', 50000.00,'salary');
INSERT INTO job VALUES (18, 13579, 'HI005', 'full-time', 150000, 'salary');
INSERT INTO job VALUES (19, 13579, 'AB001', 'part-time', 25.00,'wage');
INSERT INTO job VALUES (20, 24680, 'BC002', 'full-time', 78000.00,'salary');
INSERT INTO job VALUES (21, 34679, 'DE003', 'part-time', 200000.00,'salary');
INSERT INTO job VALUES (22, 46790, 'FG004', 'part-time', 50.00,'wage');
INSERT INTO job VALUES (23, 57698, 'HI005', 'full-time', 120000.00,'salary');
INSERT INTO job VALUES (24, 22491, 'BC002', 'full-time', 35000, 'salary');
INSERT INTO job VALUES (25, 22491, 'FG004', 'part-time', 15.00, 'wage');
INSERT INTO job VALUES (26, 24680, 'BC002', 'full-time', 66000.00,'salary');
INSERT INTO job VALUES (27, 13579, 'HI005', 'full-time', 45000, 'salary');

INSERT INTO tool VALUES ('243', 'Oracle Database');
INSERT INTO tool VALUES ('4', 'Java');
INSERT INTO tool VALUES ('230', 'IDAPro');

INSERT INTO certificate VALUES ('1', 'Oracle Database Developer I', 'Able to use Oracle databases.', '243', TO_DATE('2017-DEC-6 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 22491);
INSERT INTO certificate VALUES ('2', 'Oracle Database Developer II', 'Able to use Oracle databases.', '243', TO_DATE('2017-DEC-6 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 22491);
INSERT INTO certificate VALUES ('3', 'Reverse engineering with IDAPro', 'Able to use IDAPro.', '230', TO_DATE('2018-DEC-6 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 22491);
INSERT INTO certificate VALUES ('4', 'Java Mastery', 'Strong Java skills.', '4', TO_DATE('2019-DEC-6 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 22491);
INSERT INTO certificate VALUES ('5', 'Design Patterns', 'Advanced level design patterns.', '4', TO_DATE('2019-DEC-6 12:00','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'), 22491);

INSERT INTO gives_certificate VALUES ('CS-101', '4');
INSERT INTO gives_certificate VALUES ('CS-347', '1');
INSERT INTO gives_certificate VALUES ('CS-347', '2');
INSERT INTO gives_certificate VALUES ('CS-405', '5');

INSERT INTO requires_certificate VALUES ('HI005', '1');
INSERT INTO requires_certificate VALUES ('HI005', '2');
INSERT INTO requires_certificate VALUES ('JK006', '4');
INSERT INTO requires_certificate VALUES ('FG004', '3');
INSERT INTO requires_certificate VALUES ('BC002', '5');

INSERT INTO has_certificate VALUES ('3', '1');
INSERT INTO has_certificate VALUES ('3', '2');

INSERT INTO takes VALUES (1, 'CS-319', 2, 2016);
INSERT INTO takes VALUES (1, 'CS-101', 2, 2016);
INSERT INTO takes VALUES (2, 'EE-181', 1, 2016);
INSERT INTO takes VALUES (3, 'CS-315', 1, 2017);
INSERT INTO takes VALUES (4, 'CS-315', 3, 2017);
INSERT INTO takes VALUES (5, 'CS-190', 2, 2017);

INSERT INTO requires VALUES('AB001','OOP100');
INSERT INTO requires VALUES('BC002','OOP100');
INSERT INTO requires VALUES('DE003','OOP100');
INSERT INTO requires VALUES('FG004','OOP100');
INSERT INTO requires VALUES('HI005','DBA500');
INSERT INTO requires VALUES('HI005','ER620');
INSERT INTO requires VALUES('JK006','OOP100');

INSERT INTO covers VALUES('CS-101','OOP100');
INSERT INTO covers VALUES('CS-190','GD200');
INSERT INTO covers VALUES('CS-315','ROB304');
INSERT INTO covers VALUES('CS-319','IMG420');
INSERT INTO covers VALUES('CS-347','DBA500');
INSERT INTO covers VALUES('CS-347','ER620');

INSERT INTO has VALUES(1, 'OOP100');
INSERT INTO has VALUES(2, 'OOP100');
INSERT INTO has VALUES(3, 'ER620');
INSERT INTO has VALUES (3, 'DBA500');
INSERT INTO has VALUES(4, 'OOP100');
INSERT INTO has VALUES(4, 'IMG420');
INSERT INTO has VALUES(4, 'DBA500');
INSERT INTO has VALUES(5, 'GD200');

INSERT INTO worked VALUES(1, 1);
INSERT INTO worked VALUES(2, 2);
INSERT INTO worked VALUES(3, 3);
INSERT INTO worked VALUES(4, 4);
INSERT INTO worked VALUES(5, 5);
INSERT INTO worked VALUES(6, 6);
INSERT INTO worked VALUES (7,7);
INSERT INTO worked VALUES (8,8);
INSERT INTO worked VALUES (12,12);
INSERT INTO worked VALUES (13,13);
INSERT INTO worked VALUES (15,15);
INSERT INTO worked VALUES (16,16);
INSERT INTO worked VALUES (17,17);
INSERT INTO worked VALUES (18,18);
INSERT INTO worked VALUES (19,27);
INSERT INTO worked VALUES (20,20);

INSERT INTO works VALUES (17,20);
INSERT INTO works VALUES (2,27);
INSERT INTO works VALUES (15,17);
INSERT INTO works VALUES (4,1);
INSERT INTO works VALUES (5,22);
INSERT INTO works VALUES (25,3);
INSERT INTO works VALUES (24,12);
INSERT INTO works VALUES (8,13);
INSERT INTO works VALUES (9,24);
INSERT INTO works VALUES (10,20);
INSERT INTO works VALUES (11,21);
INSERT INTO works VALUES (3,2);
INSERT INTO works VALUES (6,7);
INSERT INTO works VALUES (14,9);
INSERT INTO works VALUES (7,17);
INSERT INTO works VALUES (19,26);
INSERT INTO works VALUES (20,25);
