SELECT jp_code, title
FROM job_profile JP
WHERE NOT EXISTS (SELECT skill_code
                  FROM requires R
                  WHERE JP.jp_code = R.jp_code
                  MINUS
                  SELECT skill_code
                  FROM has H
                  WHERE per_id=2)
                  AND NOT EXISTS
                  (SELECT cer_code
                   FROM requires_certificate RC
                   WHERE JP.jp_code = RC.jp_code
                   MINUS
                   SELECT cer_code
                   FROM has_certificate HC
                   WHERE HC.per_id=2);
