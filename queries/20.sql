with jp_required_skills as (
  select jp_code, skill_code
  from job_profile natural join requires
),
needed_skill_count as (
  select per_id,
        (select count(skill_code)
          from (select skill_code
                from jp_required_skills R
                where R.jp_code = 'HI005'
                minus
                select skill_code
                from has H
                where H.per_id = P.per_id))
          as needed_skill
  from person P
),
missing_k as (
  select *
  from needed_skill_count
  where needed_skill <= 2
  order by needed_skill asc
),
missing_skills as (
  select per_id, skill_code
  from person natural join jp_required_skills
  where jp_code = 'HI005'
  minus
  select per_id, skill_code
  from person natural join has
)

select skill_code, count(per_id) as num_people_missing
from missing_k natural join missing_skills
group by skill_code
order by num_people_missing desc;
