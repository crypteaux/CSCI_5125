WITH skills_list AS (
  SELECT skill_code
  FROM requires
  WHERE jp_code = 'HI005'
),
missing_one AS (
  SELECT per_id, name
  FROM person P
  WHERE 1 = (SELECT count(skill_code)
                    FROM (SELECT skill_code
                          FROM job_profile NATURAL JOIN requires
                          WHERE jp_code = 'HI005'
                          MINUS
                          SELECT skill_code
                          FROM has H
                          WHERE H.per_id = P.per_id))
)

SELECT skill_code, COUNT(per_id)
FROM (SELECT per_id, skill_code
      FROM missing_one M, skills_list S
      MINUS
      SELECT per_id, skill_code
      FROM missing_one NATURAL JOIN has)
GROUP BY skill_code;
