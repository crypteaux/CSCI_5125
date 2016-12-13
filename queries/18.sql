WITH required_skills AS (
  SELECT skill_code
  FROM requires
  WHERE jp_code = 'HI005'
),
missing_skills (per_id, num_skills_missed) AS (
  SELECT per_id, COUNT(skill_code) as num_skills_missed
  FROM person P, required_skills J
  WHERE EXISTS (SELECT skill_code
                FROM required_skills
                WHERE skill_code = J.skill_code
                MINUS
                SELECT skill_code
                FROM HAS
                WHERE per_id = P.per_id)
  GROUP BY per_id
)

SELECT per_id, num_skills_missed
FROM missing_skills
WHERE num_skills_missed = (SELECT MIN(num_skills_missed)
                           FROM missing_skills);
