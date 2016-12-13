WITH computed_pay AS (
  SELECT J.job_code,
    CASE
      WHEN J.pay_type = 'salary' THEN J.pay_rate
      WHEN J.pay_type = 'wage' THEN J.pay_rate * 1920
      ELSE NULL
    END
    "Total Pay"
  FROM job J
),
current_pay AS (
  SELECT per_id, "Total Pay", primary_sector
  FROM works NATURAL JOIN job NATURAL JOIN company NATURAL JOIN computed_pay
),
old_pay as(
  SELECT per_id, "Total Pay", primary_sector
  FROM worked NATURAL JOIN job NATURAL JOIN company NATURAL JOIN computed_pay
),
num_increase as(
  SELECT primary_sector, COUNT(DISTINCT per_id) AS increase
  FROM current_pay C
  WHERE EXISTS (SELECT I.per_id
                FROM old_pay I
                WHERE C.per_id = I.per_id)
  AND "Total Pay" > (SELECT MAX("Total Pay")
                     FROM old_pay O
                     WHERE C.per_id = O.per_id)
  GROUP BY primary_sector
),
num_decrease as(
  SELECT primary_sector, COUNT(DISTINCT per_id) AS decrease
  FROM current_pay C
  WHERE EXISTS (SELECT I.per_id
                FROM old_pay I
                WHERE C.per_id = I.per_id)
  AND "Total Pay" < (SELECT MAX("Total Pay")
                     FROM old_pay O
                     WHERE C.per_id = O.per_id)
  GROUP BY primary_sector
)

SELECT D.primary_sector, I.increase, D.decrease,
  CASE
      WHEN I.increase IS NULL THEN 1/D.decrease
      WHEN D.decrease IS NULL THEN I.increase/1
      ELSE I.increase/D.decrease
   END
   AS total_ratio
FROM num_decrease D FULL JOIN num_increase I
ON D.primary_sector = I.primary_sector;
