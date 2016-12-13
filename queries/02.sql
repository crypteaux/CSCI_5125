SELECT name, pay_rate
FROM person NATURAL JOIN works NATURAL JOIN job NATURAL JOIN company
WHERE comp_name = 'Google'
AND pay_type = 'salary'
ORDER BY pay_rate DESC;
