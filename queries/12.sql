-- a sequence to generate course set ID
CREATE SEQUENCE CourseSet_seq
START WITH 1
INCREMENT BY 1
MAXVALUE 999999
NOCYCLE;

create table CourseSet
(
  csetID number(8,0),
  c_code1 number(6,0),
  c_code2 number(6,0),
  c_code3 number(6,0),
  cs_size number(2,0),
  primary key(csetID)
);

-- two-course set
INSERT INTO CourseSet
SELECT CourseSet_seq.NEXTVAL, C1.c_code, C2.c_code, null
FROM Course C1, Course C2
WHERE C1.c_code < C2.c_code;

-- three-course set
INSERT INTO CourseSet
SELECT CourseSet_seq.NEXTVAL, C1.c_code, C2.c_code, C3.c_code
FROM Course C1, Course C2, Course C3
WHERE C1.c_code < C2.c_code AND C2.c_code < C3.c_code;

-- the relationship between course set and its covering skills
WITH CourseSet_Skill(csetID, skill_code) AS (
  SELECT csetID, skill_code
  FROM CourseSet CSet JOIN covers CS ON CSet.c_code1=CS.c_code
  UNION
  SELECT csetID, skill_code
  FROM CourseSet CSet JOIN covers CS ON CSet.c_code2=CS.c_code
  UNION
  SELECT csetID, skill_code
  FROM CourseSet CSet JOIN covers CS ON CSet.c_code3=CS.c_code
),
CourseSet_Certificate(csetID, cer_code) AS (
  SELECT csetID, cer_code
  FROM CourseSet CSet JOIN gives_certificate CS ON CSet.c_code1=CS.c_code
  UNION
  SELECT csetID, cer_code
  FROM CourseSet CSet JOIN gives_certificate CS ON CSet.c_code2=CS.c_code
  UNION
  SELECT csetID, cer_code
  FROM CourseSet CSet JOIN gives_certificate CS ON CSet.c_code3=CS.c_code
),
missing_skills AS (
  SELECT skill_code
  FROM job NATURAL JOIN job_profile NATURAL JOIN requires
  WHERE job_code = 5
  MINUS
  SELECT skill_code
  FROM person NATURAL JOIN has NATURAL JOIN skill
  WHERE per_id = 5
),
missing_certificates AS (
  SELECT cer_code
  FROM job NATURAL JOIN job_profile NATURAL JOIN requires_certificate
  WHERE job_code = 5
  MINUS
  SELECT cer_code
  FROM person NATURAL JOIN has_certificate
  WHERE per_id = 5
),

-- use division to find those course sets that cover missing skills
Cover_CSet(csetID, cs_size) AS (
  SELECT csetID, cs_size
  FROM CourseSet CSet
  WHERE NOT EXISTS (SELECT skill_code
                    FROM missing_skills
                    MINUS
                    SELECT skill_code
                    FROM CourseSet_Skill CSSk
                    WHERE CSSk.csetID = CSet.csetID)
                    AND NOT EXISTS
                    (SELECT cer_code
                    FROM missing_certificates
                    MINUS
                    SELECT cer_code
                    FROM CourseSet_Certificate CSC
                    WHERE CSC.csetID = CSet.csetID)
)

-- to find the smallest sets
SELECT c_code1, c_code2, c_code3
FROM Cover_CSet NATURAL JOIN CourseSet
WHERE cs_size = (SELECT MIN(cs_size) FROM Cover_CSet);
