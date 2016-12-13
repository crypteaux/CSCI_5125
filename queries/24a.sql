WITH employee_count as (
  SELECT comp_id, COUNT (per_id) AS count_employees
  FROM works NATURAL JOIN job NATURAL JOIN company
  GROUP BY comp_id
),
business_sector AS(
  SELECT primary_sector, SUM(count_employees) as sector_employee_count
  FROM employee_count NATURAL JOIN company
  GROUP BY primary_sector
)

SELECT primary_sector, sector_employee_count
FROM business_sector
WHERE sector_employee_count = (SELECT MAX(sector_employee_count)
                               FROM business_sector);
