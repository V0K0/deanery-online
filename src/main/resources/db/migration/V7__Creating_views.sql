create or replace view full_teacher_info as
select r.teacher_id, t.lastname, t.name, sub.subject_name
from subject_teacher_relation r
join teacher t on r.teacher_id = t.teacher_id
join subject sub on r.subject_id = sub.subject_id;

create or replace view full_student_info as
select s.id, s.lastname, s.name, f.faculty_name, d.department_name, spec.specialty_name, g.group_code
from student s
join university_group g on s.group_id = g.group_id
join specialty spec on g.specialty_id = spec.specialty_id
join department d on d.department_id = spec.department_id
join faculty f on f.faculty_id = d.faculty_id;

create or replace view detail_schedule as
select s.subject_name, sch.day_of_week, sch.week_type, sch.lesson_time, sch.lesson_place, gr.group_code
from subject s
join schedule sch on sch.subject_id = s.subject_id
join university_group gr on sch.group_id = gr.group_id;
