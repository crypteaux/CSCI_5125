SELECT title
FROM skill NATURAL JOIN
    (SELECT skill_code
      FROM requires NATURAL JOIN job
      WHERE job_code = 'DBA001'
      MINUS
      SELECT skill_code
      FROM has
      WHERE per_id = 1);
