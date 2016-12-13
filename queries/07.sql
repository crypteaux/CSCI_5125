SELECT skill_code, title, description, skill_level
FROM requires NATURAL JOIN skill
WHERE jp_code = 'HI005';
