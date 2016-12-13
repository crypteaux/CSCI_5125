--list the courses that can provide the most jobless
--people with the skills and certificates necessary
--to get a job of the job profiles
--that have the most openings due to lack of qualified workers

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
jobs_lacking_qualified AS (
  SELECT jp_code
  FROM difference
  WHERE "Difference" = (SELECT MAX("Difference")
                        FROM difference)
)

SELECT c_code
FROM jobs_lacking_qualified NATURAL JOIN requires NATURAL JOIN covers
UNION
SELECT c_code
FROM jobs_lacking_qualified NATURAL JOIN requires_certificate NATURAL JOIN gives_certificate;
