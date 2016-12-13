SELECT name, title
FROM person NATURAL JOIN works NATURAL JOIN job NATURAL JOIN job_profile
WHERE name = 'Daniel DeKerlegand'
UNION
SELECT name, title
FROM person NATURAL JOIN worked NATURAL JOIN job NATURAL JOIN job_profile
WHERE name = 'Daniel DeKerlegand';
