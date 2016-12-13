-- list the course sets that can together train
-- an unemployed worker for the job profiles
-- that have the most openings due to
-- lack of skilled workers

WITH unfilled_jobs AS (
  SELECT job.jp_code, COUNT(job.job_code) AS vacancies
  FROM job LEFT OUTER JOIN works
  ON job.job_code = works.job_code
  WHERE per_id IS NULL
  GROUP BY jp_code
),
unemployed AS (
  SELECT person.per_id
  FROM person LEFT OUTER JOIN works
  ON person.per_id = works.per_id
  WHERE job_code IS NULL
),
qualified AS (
  SELECT UJ.jp_code, COUNT(per_id) AS qualified_unemployed
  FROM unemployed P, unfilled_jobs UJ
  WHERE NOT EXISTS (SELECT skill_code
                FROM requires R
                WHERE R.jp_code = UJ.jp_code
                MINUS
                SELECT skill_code
                FROM has H
                WHERE H.per_id = P.per_id)
  AND NOT EXISTS (SELECT cer_code
             FROM requires_certificate RC
             WHERE RC.jp_code = UJ.jp_code
             MINUS
             SELECT cer_code
             FROM has_certificate HC
             WHERE HC.per_id = P.per_id)
  GROUP BY UJ.jp_code
),
difference AS (
  SELECT U.jp_code,
    CASE
        WHEN Q.qualified_unemployed IS NOT NULL THEN U.vacancies - Q.qualified_unemployed
        ELSE U.vacancies
      END
      "Difference"
  FROM unfilled_jobs U LEFT JOIN qualified Q ON U.jp_code = Q.jp_code
),
jobs_lacking_qualified as (
  SELECT jp_code
  FROM difference
  WHERE "Difference" = (SELECT MAX("Difference")
                        FROM difference)
),
CourseSet_Skill(csetID, skill_code) AS (
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
  FROM jobs_lacking_qualified NATURAL JOIN job_profile NATURAL JOIN requires
),
missing_certificates AS (
  SELECT cer_code
  FROM jobs_lacking_qualified NATURAL JOIN job_profile NATURAL JOIN requires_certificate
),
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
SELECT DISTINCT c_code1, c_code2, c_code3
FROM Cover_CSet NATURAL JOIN CourseSet
WHERE cs_size = (SELECT MIN(cs_size) FROM Cover_CSet);
