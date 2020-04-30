insert into faculty (faculty_name) values ('Факультет інформаційних технологій');

insert into department (faculty_id, department_name) values (1, 'ПЗКС'), (1, 'БІТ'), (1, 'САУ'), (1, 'ІСТТ');

select department_id into @pzks from department where department_name = 'ПЗКС';
select department_id into @bit from department where department_name = 'БІТ';
select department_id into @say from department where department_name = 'САУ';
select department_id into @ictt from department where department_name = 'ІСТТ';

insert into specialty (department_id, specialty_name, specialty_code) values
 (@pzks, 'Інженерія програмного забезпечення', 121),
 (@pzks, 'Комп''ютерні науки', 122),
 (@bit, 'Кібербезпека', 125),
 (@bit, 'Телекомунікації та радіотехніка', 172),
 (@say, 'Системний аналіз', 124),
 (@ictt, 'Комп''ютерна інженерія', 123),
 (@ictt, 'Інформаційні системи та технології', 126);

insert into studying_plan (plan_id) values (121), (122), (123), (124), (125), (126) ,(172);

select specialty_id into @s121 from specialty where specialty_code = 121;
select specialty_id into @s122 from specialty where specialty_code = 122;
select specialty_id into @s123 from specialty where specialty_code = 123;
select specialty_id into @s124 from specialty where specialty_code = 124;
select specialty_id into @s125 from specialty where specialty_code = 125;
select specialty_id into @s126 from specialty where specialty_code = 126;
select specialty_id into @s172 from specialty where specialty_code = 172;

insert into university_group (specialty_id, plan_id, group_code) values
(@s121, 121, '121-18-1'),
(@s121, 121, '121-18-2'),
(@s122, 122, '122-18-1'),
(@s122, 122, '122-18-2'),
(@s122, 122, '122-18-3'),
(@s123, 123, '123-18-1'),
(@s124, 124, '124-18-1'),
(@s125, 125,'125-18-1'),
(@s125, 125,'125-18-2'),
(@s126, 126,'126-18-1'),
(@s172, 172,'172-18-1');