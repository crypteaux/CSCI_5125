WITH computed_pay AS (
  SELECT comp_id, J.job_code,
    CASE
      WHEN J.pay_type = 'salary' THEN J.pay_rate
      WHEN J.pay_type = 'wage' THEN J.pay_rate * 1920
      ELSE NULL
    END
    "Total Pay"
  FROM job J
),
company_by_pay AS (
  SELECT comp_id, SUM("Total Pay") AS total_pay
  FROM computed_pay
  GROUP BY comp_id
),
sector_by_pay AS(
  SELECT primary_sector, SUM(total_pay) as sector_pay
  FROM company_by_pay NATURAL JOIN company
  GROUP BY primary_sector
)

SELECT primary_sector, sector_pay
FROM sector_by_pay
WHERE sector_pay = (SELECT MAX(sector_pay)
                    FROM sector_by_pay);
