select teacher_id into @kabak from teacher where name = 'Леонід' and lastname = 'Кабак';
select teacher_id into @beshta from teacher where name = 'Лілія' and lastname = 'Бешта';
select teacher_id into @reuta from teacher where name = 'Олександр' and lastname = 'Реута';
select teacher_id into @spirincev from teacher where name = 'В''ячеслав' and lastname = 'Спірінцев';
select teacher_id into @kozyr from teacher where name = 'Світлана' and lastname = 'Козир';
select teacher_id into @slesarev from teacher where name = 'Володимир' and lastname = 'Слесарєв';
select teacher_id into @hodenko from teacher where name = 'Олена' and lastname = 'Годенко-Наконечна';
select teacher_id into @zaharchuk from teacher where name = 'Олексій' and lastname = 'Захарчук';
select teacher_id into @sirotkina from teacher where name = 'Олена' and lastname = 'Сироткіна';
select teacher_id into @gnatushenko from teacher where name = 'Вікторія' and lastname = 'Гнатушенко';

select subject_id into @km from subject where subject_name = 'Комп''ютерні мережі';
select subject_id into @math from subject where subject_name = 'Теорія ймовірностей та мат.статистика';
select subject_id into @db from subject where subject_name = 'Організація баз даних та знань';
select subject_id into @ptic from subject where subject_name = 'Проектування та тестування інформаційних систем';
select subject_id into @pj from subject where subject_name = 'Програмування на Java';
select subject_id into @fil from subject where subject_name = 'Ціннісні компетенції фахівця';

insert into subject_teacher_relation (teacher_id, subject_id) values
(@kabak, @db),
(@beshta , @km),
(@reuta , @pj),
(@spirincev, @pj),
(@kozyr , @math),
(@slesarev , @math),
(@hodenko , @fil),
(@zaharchuk , @fil),
(@sirotkina , @ptic),
(@gnatushenko , @ptic);
