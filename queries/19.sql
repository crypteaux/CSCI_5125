WITH needed_skill_count AS (
  SELECT per_id,
        (SELECT COUNT(skill_code)
          FROM (SELECT skill_code
                FROM job_profile NATURAL JOIN requires
                WHERE jp_code = 'HI005'
                MINUS
                SELECT skill_code
                FROM has H
                WHERE H.per_id = P.per_id))
          AS needed_skill
  FROM person P
)

SELECT *
FROM needed_skill_count
WHERE needed_skill <= 2
ORDER BY needed_skill ASC;
