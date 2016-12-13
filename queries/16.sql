SELECT per_id, name
FROM person P
WHERE 1 = (SELECT COUNT(skill_code)
                  FROM (SELECT skill_code
                        FROM job_profile NATURAL JOIN requires
                        WHERE jp_code = 'HI005'
                        MINUS
                        SELECT skill_code
                        FROM has H
                        WHERE H.per_id = P.per_id));
