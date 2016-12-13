WITH unemployed AS (
  SELECT person.per_id, person.name
  FROM person LEFT OUTER JOIN works ON person.per_id = works.per_id
  WHERE job_code IS NULL
)

SELECT per_id, name
FROM unemployed NATURAL JOIN worked NATURAL JOIN job NATURAL JOIN job_profile
WHERE jp_code = 'BC002';
