package com.vozniuk.deanery.controller.api;

import com.vozniuk.deanery.data.university.StudyingPlan;
import com.vozniuk.deanery.data.university.Subject;
import com.vozniuk.deanery.data.university.Teacher;
import com.vozniuk.deanery.service.PlanService;
import com.vozniuk.deanery.service.SubjectService;
import com.vozniuk.deanery.service.TeacherService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class TeacherRelationController {

    private final TeacherService teacherService;
    private final PlanService planService;
    private final SubjectService subjectService;

    private final Logger logger = LogManager.getLogger(TeacherRelationController.class);


    @Autowired
    public TeacherRelationController(TeacherService teacherService, PlanService planService, SubjectService subjectService) {
        this.teacherService = teacherService;
        this.planService = planService;
        this.subjectService = subjectService;
    }

    @PostMapping("/teachers/relations")
    @ResponseBody
    public Object createRelation(@RequestParam(name = "id") long teacherId,
                                 @RequestParam(name = "name") String name,
                                 @RequestParam(name = "plan") long planId) {

        try {
            Teacher teacher = teacherService.getTeacherById(teacherId);
            StudyingPlan studyingPlan = planService.getPlanById(planId);
            Subject subject = subjectService.getByNameAndPlan(name, studyingPlan);

            if (studyingPlan != null && teacher != null && subject != null) {
                Set<Subject> teacherSubjects = teacher.getSubjects();
                teacherSubjects.add(subject);
                Set<Teacher> teachers = subject.getTeachers();
                teachers.add(teacher);
                teacherService.addOrUpdateTeacher(teacher);
                subjectService.addOrUpdateSubject(subject);
                logger.info("Successfully created new relation between teacher with id: {} and subject: {}", teacherId, name);
                return ResponseEntity.status(HttpStatus.CREATED).body("Created");
            }
        } catch (EntityNotFoundException e) {
            logger.error("Failed to create relation cause: one of entities not exists!");
        } catch (Exception e) {
            logger.error("Failed to create relation between teacher with id: {} and subject: {} cause: {}", teacherId, name, e.getCause());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Denied");
    }

    @DeleteMapping("/teachers/relations/{id}")
    @ResponseBody
    public Object deleteRelation(@PathVariable(name = "id") long teacherId,
                                 @RequestParam(name = "name") String name,
                                 @RequestParam(name = "plan") String plan) {

        try {
            Teacher teacher = teacherService.getTeacherById(teacherId);
            StudyingPlan studyingPlan = planService.getPlanById(Long.parseLong(plan));
            Subject subject = subjectService.getByNameAndPlan(name, studyingPlan);

            if (studyingPlan != null && teacher != null && subject != null) {
                Set<Subject> teacherSubjects = teacher.getSubjects();
                Set<Teacher> teachers = subject.getTeachers();
                boolean removeSubject = teacherSubjects.remove(subject);
                boolean removeTeacher = teachers.remove(teacher);
                if (removeSubject && removeTeacher) {
                    teacherService.addOrUpdateTeacher(teacher);
                    subjectService.addOrUpdateSubject(subject);
                    logger.info("Successfully removed relation between teacher with id: {} and subject: {}", teacherId, name);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
                }
            }
        } catch (EntityNotFoundException e) {
            logger.error("Failed to remove relation cause: one of entities not exists!");
        } catch (Exception e) {
            logger.error("Failed to remove relation between teacher with id: {} and subject: {} cause: {}", teacherId, name, e.getCause());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Denied");
    }

}
