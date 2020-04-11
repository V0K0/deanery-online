create table teacher (
teacher_id int not null,
name varchar (255),
lastname  varchar (255),
patronymic  varchar (255),
phone varchar(12),
primary key (teacher_id));

alter table university_group add constraint group_spec_fk foreign key (specialty_id) references specialty (specialty_id);

alter table student add phone varchar (12) after group_id;

alter table subject add defence_date date after course_work, add teacher_id int after defence_date;

alter table student modify group_id int null;

alter table subject add constraint sub_teach_fk foreign key (teacher_id) references teacher (teacher_id);