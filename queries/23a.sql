WITH employee_count as (
  SELECT comp_id, COUNT (DISTINCT per_id) AS count_employees
  FROM works NATURAL JOIN job NATURAL JOIN company
  GROUP BY comp_id
)

SELECT comp_id, count_employees
FROM employee_count
WHERE count_employees = (SELECT MAX(count_employees)
                         FROM employee_count);
