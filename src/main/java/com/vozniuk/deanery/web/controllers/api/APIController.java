package com.vozniuk.deanery.web.controllers.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.vozniuk.deanery.domain.data.university.*;
import com.vozniuk.deanery.json.converters.CollectionToJSONConverter;
import com.vozniuk.deanery.json.serializers.StudentSerializer;
import com.vozniuk.deanery.json.serializers.SubjectSerializer;
import com.vozniuk.deanery.json.serializers.TeacherSerializer;
import com.vozniuk.deanery.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class APIController {

    private SubjectServiceImpl subjectServiceImpl;

    private StudentServiceImpl studentServiceImpl;

    private GroupServiceImpl groupServiceImpl;

    private TeacherServiceImpl teacherServiceImpl;

    private PlanServiceImpl planServiceImpl;

    private final Logger logger = LogManager.getLogger(APIController.class);

    @Autowired
    public void setSubjectServiceImpl(SubjectServiceImpl subjectServiceImpl) {
        this.subjectServiceImpl = subjectServiceImpl;
    }

    @Autowired
    public void setStudentServiceImpl(StudentServiceImpl studentServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;
    }

    @Autowired
    public void setGroupServiceImpl(GroupServiceImpl groupServiceImpl) {
        this.groupServiceImpl = groupServiceImpl;
    }

    @Autowired
    public void setTeacherServiceImpl(TeacherServiceImpl teacherServiceImpl) {
        this.teacherServiceImpl = teacherServiceImpl;
    }

    @Autowired
    public void setPlanServiceImpl(PlanServiceImpl planServiceImpl) {
        this.planServiceImpl = planServiceImpl;
    }


    @GetMapping(value = "api/subjects", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getSubjects(
            @RequestParam(name = "page", defaultValue = "1") String page, Model model) {
        int pageNumIndex = Integer.parseInt(page) - 1;
        Pageable pageable = PageRequest.of(pageNumIndex, 10);
        Page<Subject> subjects = subjectServiceImpl.findAllLimit(pageable);
        List<Subject> subjectList = subjects.toList();
        String json = CollectionToJSONConverter.writeAsJSON(Subject.class, new SubjectSerializer(), subjectList);
        if (json != null) {
            logger.info("JSON of subjects successfully created");
            return ResponseEntity.ok(json);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


    @GetMapping(value = "api/teachers", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getTeachers(
            @RequestParam(name = "page", defaultValue = "1") String page) {
        int pageNumIndex = Integer.parseInt(page) - 1;
        Pageable pageable = PageRequest.of(pageNumIndex, 10);
        Page<Teacher> teachers = teacherServiceImpl.findAllLimit(pageable);
        if (teachers != null) {
            List<Teacher> teachersList = teachers.toList();
            String json = CollectionToJSONConverter.writeAsJSON(Teacher.class, new TeacherSerializer(), teachersList);
            if (json != null) {
                logger.info("JSON of teachers successfully created");
                return ResponseEntity.ok(json);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


    @GetMapping(value = "api/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getStudents(
            @RequestParam(name = "page", defaultValue = "1") String page) {
        int pageNumIndex = Integer.parseInt(page) - 1;
        Pageable pageable = PageRequest.of(pageNumIndex, 10);
        Page<Student> students = studentServiceImpl.findAllLimit(pageable);
        if (students != null) {
            List<Student> studentsList = students.toList();
            String json = CollectionToJSONConverter.writeAsJSON(Student.class, new StudentSerializer(), studentsList);
            if (json != null) {
                logger.info("JSON of students successfully created");
                return ResponseEntity.ok(json);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


    @GetMapping(value = "api/students/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getStudentsByGroup(@RequestParam(name = "group") String groupCode) {
        UniversityGroup group = groupServiceImpl.getByGroupCode(groupCode);
        if (group != null) {
            List<Student> studentsList = studentServiceImpl.getAllStudentsByGroup(group);
            if (studentsList != null && !studentsList.isEmpty()) {
                String json = CollectionToJSONConverter.writeAsJSON(Student.class, new StudentSerializer(), studentsList);
                if (json != null) {
                    logger.info("JSON of students filtered by group successfully created");
                    return ResponseEntity.ok(json);
                }
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


    @GetMapping(value = "api/teachers/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getTeachersByNameAndLastname(@RequestParam(name = "name") String name, @RequestParam(name = "lastname") String lastname) {
        Teacher teacher = teacherServiceImpl.getByNameAndLastname(name, lastname);
        if (teacher != null) {
            String json = convertTeacherToJSON(teacher);
            if (json != null) {
                logger.info("JSON of concrete teacher successfully created");
                return ResponseEntity.ok(json);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


    @GetMapping(value = "api/subjects/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getSubjectsByPlan(@RequestParam(name = "plan") String plan) {
        StudyingPlan studyingPlan = planServiceImpl.getPlanById(Integer.parseInt(plan));
        if (studyingPlan != null) {
            List<Subject> subjectList = subjectServiceImpl.getAllByPlan(studyingPlan);
            String json = CollectionToJSONConverter.writeAsJSON(Subject.class, new SubjectSerializer(), subjectList);
            if (json != null) {
                logger.info("JSON of subjects filtered by plan successfully created");
                return ResponseEntity.ok(json);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    private String convertTeacherToJSON(Teacher teacher) {
        final ObjectMapper mapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Teacher.class, new TeacherSerializer());
        mapper.registerModule(simpleModule);
        try {
            return mapper.writeValueAsString(teacher);
        } catch (JsonProcessingException e) {
            return null;
        }
    }



}
