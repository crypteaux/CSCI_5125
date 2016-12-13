WITH missing_skills AS (
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
complete_courses AS (
  SELECT DISTINCT c_code, title
  FROM course C
  WHERE NOT EXISTS (SELECT skill_code
                    FROM missing_skills
                    MINUS
                    SELECT skill_code
                    FROM covers J
                    WHERE C.c_code = J.c_code)
                    AND NOT EXISTS
                    (SELECT cer_code
                    FROM missing_certificates
                    MINUS
                    SELECT cer_code
                    FROM gives_certificate GC
                    WHERE C.c_code = GC.c_code)
)

SELECT c_code, title, sec_no, complete_date
FROM complete_courses NATURAL JOIN section
WHERE complete_date = (SELECT MIN(complete_date)
                       FROM complete_courses NATURAL JOIN section);
