WITH computed_pay AS (
  SELECT comp_id, J.job_code,
    CASE
      WHEN J.pay_type = 'salary' THEN J.pay_rate
      WHEN J.pay_type = 'wage' THEN J.pay_rate * 1920
      ELSE NULL
    END
    "Total Pay"
  FROM job J
)

SELECT comp_id, SUM("Total Pay") AS total_pay
FROM computed_pay
GROUP BY comp_id
ORDER BY total_pay DESC;
