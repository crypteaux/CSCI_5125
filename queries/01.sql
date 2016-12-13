SELECT name
FROM person
NATURAL JOIN works
NATURAL JOIN company
WHERE comp_name = 'Google';
