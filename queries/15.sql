SELECT per_id, name, email
FROM person P
WHERE NOT EXISTS (SELECT skill_code
                  FROM requires R
                  WHERE R.jp_code='AB001'
                  MINUS
                  SELECT skill_code
                  FROM has H
                  WHERE P.per_id=H.per_id)
                  AND NOT EXISTS
                  (SELECT cer_code
                   FROM requires_certificate RC
                   WHERE RC.jp_code='AB001'
                   MINUS
                   SELECT cer_code
                   FROM has_certificate HC
                   WHERE P.per_id=HC.per_id);
