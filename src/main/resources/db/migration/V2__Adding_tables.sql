create table student (
id int not null,
name varchar (255),
lastname varchar (255),
patronymic  varchar (255),
date_of_birth date,
address varchar (255),
group_id int null,
phone varchar (12),
primary key (id));

create table faculty (
faculty_id int not null AUTO_INCREMENT,
faculty_name varchar (255) not null,
primary key (faculty_id));

create table department (
department_id int not null AUTO_INCREMENT,
faculty_id int not null,
department_name varchar (255) not null,
primary key (department_id));

create table specialty (
specialty_id int not null AUTO_INCREMENT,
department_id int not null,
specialty_name varchar (255) not null,
specialty_code int (5) not null,
primary key (specialty_id));

create table university_group (
group_id int not null AUTO_INCREMENT,
specialty_id int not null,
plan_id int,
group_code varchar (10) not null,
primary key (group_id));

create table studying_plan (
plan_id int not null,
primary key (plan_id));

create table subject (
subject_id int not null AUTO_INCREMENT,
plan_id int not null,
subject_name varchar (255) not null,
practical_hours int not null,
lecture_hours int not null,
defence_type varchar (20) not null,
course_work bit,
defence_date date,
primary key (subject_id));

create table schedule (
schedule_id int not null AUTO_INCREMENT,
group_id int not null,
subject_id int not null,
term int(1) not null,
day_of_week varchar (20),
week_type varchar (20),
lesson_time time,
lesson_place varchar (255),
primary key (schedule_id));

create table teacher (
teacher_id int not null AUTO_INCREMENT,
name varchar (255),
lastname  varchar (255),
patronymic  varchar (255),
phone varchar(12),
primary key (teacher_id));

create table subject_teacher_relation (
teacher_id int not null,
subject_id int not null,
PRIMARY KEY (teacher_id, subject_id),
KEY subject_id (subject_id),
CONSTRAINT teacher_subject_ibfk_1
FOREIGN KEY (teacher_id) REFERENCES teacher (teacher_id) ON DELETE CASCADE,
CONSTRAINT teacher_subject_ibfk_2
FOREIGN KEY (subject_id) REFERENCES subject (subject_id) ON DELETE CASCADE
);


alter table department add constraint dep_fac_fk foreign key (faculty_id) references faculty (faculty_id) ON DELETE CASCADE;

alter table specialty add constraint spec_dep_fk foreign key (department_id) references department (department_id) ON DELETE CASCADE;

alter table student add constraint st_gr_fk foreign key (group_id) references university_group (group_id) ON DELETE SET NULL;

alter table university_group add constraint group_pl_fk foreign key (plan_id) references studying_plan (plan_id)  ON DELETE SET NULL;

alter table university_group add constraint group_spec_fk foreign key (specialty_id) references specialty (specialty_id) ON DELETE CASCADE;

alter table subject add constraint subj_plan_fk foreign key (plan_id) references studying_plan (plan_id) ON DELETE CASCADE;

alter table schedule add constraint sch_gr_fk foreign key (group_id) references university_group (group_id) ON DELETE CASCADE;

alter table schedule add constraint sch_subj_fk foreign key (subject_id) references subject (subject_id) ON DELETE CASCADE;
