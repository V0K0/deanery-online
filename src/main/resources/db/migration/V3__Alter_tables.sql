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

alter table student add phone varchar (12) after group_id;

alter table subject add defence_date date after course_work;
