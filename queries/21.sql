SELECT per_id, name
FROM person NATURAL JOIN worked NATURAL JOIN job NATURAL JOIN job_profile
WHERE jp_code = 'BC002';
