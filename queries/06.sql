(SELECT DISTINCT skill_code
 FROM person NATURAL JOIN works NATURAL JOIN
      job NATURAL JOIN job_profile NATURAL JOIN
      requires
 WHERE per_id = 3)
MINUS
(SELECT skill_code
 FROM person NATURAL JOIN has
 WHERE per_id = 3);
