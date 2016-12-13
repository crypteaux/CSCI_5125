WITH missing_skills AS (
  SELECT skill_code
  FROM requires
  WHERE jp_code = 'HI005'
  MINUS
  SELECT skill_code
  FROM has
  WHERE per_id = 1
),
missing_certificates AS (
  SELECT cer_code
  FROM requires_certificate
  WHERE jp_code = 'HI005'
  MINUS
  SELECT cer_code
  FROM has_certificate
  WHERE per_id = 1
)

SELECT DISTINCT c_code FROM missing_skills NATURAL JOIN covers
UNION
SELECT DISTINCT c_code FROM missing_certificates NATURAL JOIN gives_certificate;
