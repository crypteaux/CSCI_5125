create table person
  (
    per_id number not null,
    name varchar(50) not null,
    email varchar(50) not null,
    gender varchar(10) not null,
    primary key(per_id)
  );

create table phone
  (
    per_id number not null,
    phone_num varchar(20) not null,
    phone_type varchar(20) not null,
    primary key (per_id,phone_num),
    foreign key (per_id) references person
  );

create table address
  (
    per_id number not null,
    street varchar(50) not null,
    city varchar(50) not null,
    state varchar(30) not null,
    zip_code number(10,0) not null,
    primary key(per_id, street, city),
    foreign key(per_id) references person
  );

create table skill
  (
    skill_code varchar(8) not null,
    title varchar(50) not null,
    description varchar(300) not null,
    skill_level varchar(10)
      check (skill_level in ('beginner', 'medium', 'advanced')),
    primary key(skill_code)
  );

create table course
  (
    c_code varchar(8) not null,
    title varchar(50) not null,
    course_level varchar(10)
      check (course_level in ('beginner', 'medium', 'advanced')),
    description varchar(200) not null,
    status varchar(8)
      check (status in ('active', 'expired')),
    retail_price number(8,2) not null,
    primary key (c_code)
  );

create table section
  (
    c_code varchar(8) not null,
    sec_no number not null,
    year number(4,0) not null,
    complete_date date not null,
    offered_by varchar(50) not null,
    format varchar(50)
      check (format in ('classroom', 'online-sync', 'online-selfpaced', 'correspondence')),
    price number(8,2) not null,
    primary key (c_code,sec_no,year),
    foreign key (c_code) references course
  );

create table job_profile
  (
    jp_code varchar(8) not null,
    title varchar(50) not null,
    description varchar(300) not null,
    avg_pay number(10,2) not null,
    primary key (jp_code)
  );

create table company
  (
    comp_id number not null,
    comp_name varchar(50) not null,
    primary_sector varchar(50) not null,
    website varchar(50) not null,
    primary key (comp_id)
  );

create table company_address
  (
    comp_id number not null,
    street varchar(50) not null,
    city varchar(50) not null,
    state varchar(50) not null,
    zip_code number(10,0) not null,
    primary key (comp_id, street, city, state),
    foreign key (comp_id) references company
  );

create table specialty
  (
    comp_id number not null,
    specialty_type varchar(50)
      check (specialty_type in ('construction', 'logistics service', 'computer hardware', 'software', 'electronics', 'basic materials', 'IT consulting', 'transportation')),
    primary key (comp_id, specialty_type),
    foreign key (comp_id) references company
  );

create table job
  (
    job_code number not null,
    comp_id number not null,
    jp_code varchar(8) not null,
    type varchar(10)
      check (type in ('full-time','part-time')),
    pay_rate number(8,2) not null,
    pay_type varchar(10)
      check (pay_type in ('wage','salary')),
    primary key(job_code),
    foreign key(comp_id) references company,
    foreign key(jp_code) references job_profile
  );


create table tool
  (
    t_code varchar(8) not null,
    t_name varchar(50) not null,
    primary key (t_code)
  );

create table certificate
  (
    cer_code varchar(8) not null,
    cer_title varchar(50) not null,
    cer_description varchar(200) not null,
    t_code varchar(8) not null,
    expire_date date not null,
    comp_id number not null,
    primary key (cer_code),
    foreign key (t_code) references tool,
    foreign key (comp_id) references company
  );

create table takes
  (
    per_id number not null,
    c_code varchar(8) not null,
    sec_no number not null,
    year number(4,0) not null,
    primary key(per_id,c_code,sec_no),
    foreign key(per_id) references person,
    foreign key(c_code,sec_no,year) references section
  );

create table has
  (
    per_id number not null,
    skill_code varchar(8) not null,
    primary key(per_id,skill_code),
    foreign key(per_id) references person,
    foreign key(skill_code) references skill
  );

create table requires
  (
    jp_code varchar(8) not null,
    skill_code varchar(8) not null,
    primary key(jp_code,skill_code),
    foreign key(jp_code) references job_profile,
    foreign key(skill_code) references skill
  );

create table covers
  (
    c_code varchar(8) not null,
    skill_code varchar(8) not null,
    primary key(c_code,skill_code),
    foreign key(c_code) references course,
    foreign key(skill_code) references skill
  );

create table works
  (
    per_id number not null,
    job_code number not null,
    primary key (per_id,job_code),
    foreign key (per_id) references person,
    foreign key (job_code) references job
  );

create table worked
  (
    per_id number not null,
    job_code number not null,
    primary key (per_id,job_code),
    foreign key (per_id) references person,
    foreign key (job_code) references job
  );

create table requires_certificate
  (
    jp_code varchar(8) not null,
    cer_code varchar(8) not null,
    primary key (jp_code,cer_code),
    foreign key (jp_code) references job_profile,
    foreign key (cer_code) references certificate
  );

create table has_certificate
  (
    per_id number not null,
    cer_code varchar(8) not null,
    primary key (per_id,cer_code),
    foreign key (per_id) references person,
    foreign key (cer_code) references certificate
  );

create table gives_certificate
  (
    c_code varchar(8) not null,
    cer_code varchar(8) not null,
    primary key (c_code,cer_code),
    foreign key (c_code) references course,
    foreign key (cer_code) references certificate
  );

create table CourseSet
(
  csetID number(8,0),
  c_code1 number(6,0),
  c_code2 number(6,0),
  c_code3 number(6,0),
  cs_size number(2,0),
  primary key(csetID)
);
