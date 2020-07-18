CREATE INDEX st_group_indx ON student(group_id);

CREATE INDEX gr_plan_indx ON university_group(plan_id);

CREATE INDEX gr_spec_indx ON university_group(specialty_id);

CREATE INDEX sch_subject_group_indx ON schedule(subject_id, group_id);

CREATE INDEX sub_plan_indx ON subject(plan_id);

DELIMITER //
CREATE TRIGGER lecture_insertcheck
BEFORE INSERT ON subject
FOR EACH ROW
BEGIN
IF NEW.lecture_hours < 0 THEN
	SET NEW.lecture_hours = 0;
END IF;
END//

CREATE TRIGGER week_insertcheck
BEFORE INSERT ON schedule
FOR EACH ROW
BEGIN
IF NEW.week_type != "Щотижня"  AND NEW.week_type != "Знаменник" AND NEW.week_type != "Чисельник" THEN
	SET NEW.week_type = "Щотижня";
END IF;
END//

CREATE TRIGGER practice_insertcheck
BEFORE INSERT ON subject
FOR EACH ROW
BEGIN
IF NEW.practical_hours < 0 THEN
	SET NEW.practical_hours = 0;
END IF;
END//

CREATE TRIGGER coursework_insertcheck
BEFORE INSERT ON subject
FOR EACH ROW
BEGIN
IF NEW.course_work < 0 THEN
	SET NEW.course_work = 0;
ELSEIF NEW.course_work > 1 THEN
	SET NEW.course_work = 1;
END IF;
END//

CREATE TRIGGER phone_insertcheck
BEFORE INSERT ON student
FOR EACH ROW
BEGIN
	SET new.phone = REGEXP_REPLACE(new.phone, '[^0-9]+', '');
END//

CREATE TRIGGER phone_teacher_insertcheck
BEFORE INSERT ON teacher
FOR EACH ROW
BEGIN
	SET new.phone = REGEXP_REPLACE(new.phone, '[^0-9]+', '');
END//

CREATE TRIGGER lecture_updatecheck
BEFORE UPDATE ON subject
FOR EACH ROW
BEGIN
IF NEW.lecture_hours < 0 THEN
	SET NEW.lecture_hours = 0;
END IF;
END//

CREATE TRIGGER practice_updatecheck
BEFORE UPDATE ON subject
FOR EACH ROW
BEGIN
IF NEW.practical_hours < 0 THEN
	SET NEW.practical_hours = 0;
END IF;
END//

CREATE TRIGGER coursework_updatecheck
BEFORE UPDATE ON subject
FOR EACH ROW
BEGIN
IF NEW.course_work < 0 THEN
	SET NEW.course_work = 0;
ELSEIF NEW.course_work > 1 THEN
	SET NEW.course_work = 1;
END IF;
END//

CREATE TRIGGER phone_updatecheck
BEFORE UPDATE ON student
FOR EACH ROW
BEGIN
	SET new.phone = REGEXP_REPLACE(new.phone, '[^0-9]+', '');
END//

CREATE TRIGGER phone_teacher_updatecheck
BEFORE UPDATE ON teacher
FOR EACH ROW
BEGIN
	SET new.phone = REGEXP_REPLACE(new.phone, '[^0-9]+', '');
END//

CREATE TRIGGER week_updatecheck
BEFORE UPDATE ON schedule
FOR EACH ROW
BEGIN
IF NEW.week_type != "Щотижня" AND NEW.week_type != "Знаменник" AND NEW.week_type != "Чисельник" THEN
	SET NEW.week_type = "Щотижня";
END IF;
END//

CREATE PROCEDURE count_subjects ( OUT subjects INT)
BEGIN
    SELECT COUNT(*) INTO subjects FROM subject;
END//

CREATE PROCEDURE count_students ( OUT students INT)
BEGIN
    SELECT COUNT(*) INTO students FROM student;
END//

CREATE PROCEDURE count_teachers ( OUT teachers INT)
BEGIN
    SELECT COUNT(*) INTO teachers FROM teacher;
END//

