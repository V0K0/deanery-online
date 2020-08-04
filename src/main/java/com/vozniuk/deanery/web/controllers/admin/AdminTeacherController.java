package com.vozniuk.deanery.web.controllers.admin;

import com.vozniuk.deanery.domain.data.university.StudyingPlan;
import com.vozniuk.deanery.domain.data.university.Subject;
import com.vozniuk.deanery.domain.data.university.Teacher;
import com.vozniuk.deanery.service.impl.PlanServiceImpl;
import com.vozniuk.deanery.service.impl.SubjectServiceImpl;
import com.vozniuk.deanery.service.impl.TeacherServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminTeacherController {

    private TeacherServiceImpl teacherServiceImpl;

    private SubjectServiceImpl subjectServiceImpl;

    private PlanServiceImpl planServiceImpl;

    private final Logger logger = LogManager.getLogger(AdminTeacherController.class);

    @Autowired
    public void setTeacherServiceImpl(TeacherServiceImpl teacherServiceImpl) {
        this.teacherServiceImpl = teacherServiceImpl;
    }

    @Autowired
    public void setSubjectServiceImpl(SubjectServiceImpl subjectServiceImpl) {
        this.subjectServiceImpl = subjectServiceImpl;
    }

    @Autowired
    public void setPlanServiceImpl(PlanServiceImpl planServiceImpl) {
        this.planServiceImpl = planServiceImpl;
    }

    @PutMapping("/admin-page/teachers/update")
    @ResponseBody
    public Object updateTeacher(@RequestParam Map<String, String> allParams) {
        Teacher teacher = teacherServiceImpl.getTeacherById(Integer.parseInt(allParams.get("id")));
        if (teacher != null) {
            fetchAndSetTeacherAttributes(teacher, allParams);
            teacherServiceImpl.addOrUpdateTeacher(teacher);
            logger.info("Teacher with id: {} was updated by admin", teacher.getTeacherId());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Updated");
        }
        logger.error("Failed to update teacher by admin");
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping("/admin-page/teachers/create")
    public String createTeacher(@RequestParam Map<String, String> allParams) {
        Teacher teacher = new Teacher();
        fetchAndSetTeacherAttributes(teacher, allParams);
        pushNewTeacher(teacher);
        return "redirect:/admin-page/teachers";
    }

    private void pushNewTeacher(Teacher teacher){
        if (!isTeacherExists(teacher)) {
            teacherServiceImpl.addOrUpdateTeacher(teacher);
            logger.info("Successfully added new teacher with id: {} by admin", teacher.getTeacherId());
        }
    }

    private boolean isTeacherExists(Teacher teacher) {
        return teacherServiceImpl.getByNameAndLastname(teacher.getName(), teacher.getLastname()) != null;
    }

    private void fetchAndSetTeacherAttributes(Teacher teacher, Map<String, String> allParams) {
        String name = allParams.get("name");
        String lastname = allParams.get("lastname");
        String patronymic = allParams.get("patron");
        String phone = allParams.get("phone").replace("-", "");
        setAttributes(name, lastname, patronymic, phone, teacher);
    }

    private void setAttributes(String name, String lastname, String patronymic, String phone, Teacher teacher) {
        if (name != null && !name.isBlank()) {
            teacher.setName(name);
        }
        if (lastname != null && !lastname.isBlank()) {
            teacher.setLastname(lastname);
        }
        if (patronymic != null && !patronymic.isBlank()) {
            teacher.setPatronymic(patronymic);
        }
        if (phone != null && !phone.isBlank()) {
            teacher.setPhone(phone);
        }
    }

    @PostMapping("/admin-page/teachers/add-relation")
    @ResponseBody
    public Object createRelation(@RequestParam(name = "id") String id, @RequestParam(name = "name") String name, @RequestParam(name = "plan") String plan) {
        Teacher teacher = teacherServiceImpl.getTeacherById(Integer.parseInt(id));
        StudyingPlan studyingPlan = planServiceImpl.getPlanById(Integer.parseInt(plan));
        Subject subject = subjectServiceImpl.getByNameAndPlan(name, studyingPlan);
        if (studyingPlan != null && teacher != null && subject != null) {
            Set<Subject> teacherSubjects = teacher.getSubjects();
            teacherSubjects.add(subject);
            Set<Teacher> teachers = subject.getTeachers();
            teachers.add(teacher);
            teacherServiceImpl.addOrUpdateTeacher(teacher);
            subjectServiceImpl.addOrUpdateSubject(subject);
            logger.info("Successfully created new relation between teacher with id: {} and subject: {} by admin", id, name);
            return ResponseEntity.status(HttpStatus.CREATED).body("Created");
        }
        logger.error("Failed to create relation between teacher with id: {} and subject: {} by admin", id, name);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Denied");
    }

    @DeleteMapping("/admin-page/teachers/delete-relation")
    @ResponseBody
    public Object deleteRelation(@RequestParam(name = "id") String id, @RequestParam(name = "name") String name, @RequestParam(name = "plan") String plan) {
        Teacher teacher = teacherServiceImpl.getTeacherById(Integer.parseInt(id));
        StudyingPlan studyingPlan = planServiceImpl.getPlanById(Integer.parseInt(plan));
        Subject subject = subjectServiceImpl.getByNameAndPlan(name, studyingPlan);
        if (studyingPlan != null && teacher != null && subject != null) {
            Set<Subject> teacherSubjects = teacher.getSubjects();
            Set<Teacher> teachers = subject.getTeachers();
            boolean removeSubject = teacherSubjects.remove(subject);
            boolean removeTeacher = teachers.remove(teacher);
            if (removeSubject && removeTeacher) {
                teacherServiceImpl.addOrUpdateTeacher(teacher);
                subjectServiceImpl.addOrUpdateSubject(subject);
                logger.info("Successfully removed relation between teacher with id: {} and subject: {} by admin", id, name);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
            }
        }
        logger.error("Failed to remove relation between teacher with id: {} and subject: {} by admin", id, name);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Denied");
    }

}
