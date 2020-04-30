insert into subject (plan_id, subject_name, practical_hours, lecture_hours, defence_type, course_work) values
 (122, 'Фізична культура і спорт', 90, 0, 'Залік', 0),
 (122, 'Ціннісні компетенції фахівця', 60, 120, 'Екзамен', 0),
 (122, 'Теорія ймовірностей та мат.статистика', 60, 60, 'Екзамен', 0),
 (122, 'Об''єктно-орієнтоване програмування', 75, 75, 'Екзамен', 0),
 (122, 'Операційні системи', 40, 80, 'Екзамен', 0),
 (122, 'Дискретна математика', 75, 75, 'Екзамен', 0),
 (122, 'Організація баз даних та знань', 40, 80, 'Екзамен', 1),
 (122, 'Методи оптимізації та дослідженння операцій', 30,60,'Екзамен', 0),
 (122, 'Комп''ютерні мережі', 50, 100 , 'Екзамен', 0),
 (122, 'Програмування на Java', 50, 100, 'Екзамен', 0),
 (122, 'Управління IT-проектами', 45, 90, 'Екзамен', 0),
 (122, 'Проектування та тестування інформаційних систем', 50, 100, 'Залік', 0);

select group_id into @g122_1 from university_group where group_code = '122-18-1';
select group_id into @g122_2 from university_group where group_code = '122-18-2';
select group_id into @g122_3 from university_group where group_code = '122-18-3';

select subject_id into @km from subject where subject_name = 'Комп''ютерні мережі';
select subject_id into @math from subject where subject_name = 'Теорія ймовірностей та мат.статистика';
select subject_id into @db from subject where subject_name = 'Організація баз даних та знань';
select subject_id into @ptic from subject where subject_name = 'Проектування та тестування інформаційних систем';
select subject_id into @pj from subject where subject_name = 'Програмування на Java';
select subject_id into @fil from subject where subject_name = 'Ціннісні компетенції фахівця';
select subject_id into @fizra from subject where subject_name = 'Фізична культура і спорт';


insert into schedule (group_id, subject_id, day_of_week, week_type ,lesson_time, lesson_place) values
(@g122_2, @km, 'Понеділок', 'Знаменник' ,'09:35:00', '7/1308'),
(@g122_2, @fizra, 'Понеділок', 'Щотижня' , '11:20:00', '-'),
(@g122_2, @db, 'Понеділок', 'Щотижня','12:55:00', '10/612'),
(@g122_2, @db, 'Понеділок', 'Знаменник', '14:30:00', '3/35'),

(@g122_2, @pj, 'Вівторок','Щотижня', '08:00:00', '1/74'),
(@g122_2, @pj, 'Вівторок', 'Знаменник', '09:35:00', '3/36'),
(@g122_2, @math, 'Вівторок', 'Щотижня','11:20:00', '3/6'),
(@g122_2, @fil, 'Вівторок', 'Щотижня','12:55:00', '1/64'),

(@g122_2, @ptic, 'Середа', 'Щотижня','08:00:00', '3/6'),
(@g122_2, @km, 'Середа', 'Щотижня','09:35:00', '3/6'),
(@g122_2, @ptic, 'Середа', 'Знаменник','11:20:00', '3/36'),
(@g122_2, @fil, 'Середа', 'Чисельник','12:55:00', '1/119'),
(@g122_2, @math, 'Середа', 'Знаменник','12:55:00', '7/1004');
