package com.vozniuk.springapplication.controllers.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.vozniuk.springapplication.domain.data.university.Student;
import com.vozniuk.springapplication.domain.data.university.Teacher;
import com.vozniuk.springapplication.domain.data.university.UniversityGroup;
import com.vozniuk.springapplication.json.StudentSerializer;
import com.vozniuk.springapplication.json.SubjectSerializer;
import com.vozniuk.springapplication.domain.data.university.Subject;
import com.vozniuk.springapplication.json.TeacherSerializer;
import com.vozniuk.springapplication.service.impl.GroupServiceImpl;
import com.vozniuk.springapplication.service.impl.StudentServiceImpl;
import com.vozniuk.springapplication.service.impl.SubjectServiceImpl;
import com.vozniuk.springapplication.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class APIController {

    @Autowired
    private SubjectServiceImpl subjectServiceImpl;

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @Autowired
    private GroupServiceImpl groupServiceImpl;

    @Autowired
    private TeacherServiceImpl teacherServiceImpl;

    @ResponseBody
    @GetMapping(value = "api/subjects", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getSubjects(
            @RequestParam(name = "page", defaultValue = "1") String page, Model model) {
        int pageNumIndex = Integer.parseInt(page) - 1;
        Pageable pageable = PageRequest.of(pageNumIndex, 10);
        Page<Subject> subjects = subjectServiceImpl.findAllLimit(pageable);
        List<Subject> subjectList = subjects.toList();
        String json = convertSubjectsListToJSON(subjectList);
        if (json != null) {
            return ResponseEntity.ok(json);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @ResponseBody
    @GetMapping(value = "api/teachers", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getTeachers(
            @RequestParam(name = "page", defaultValue = "1") String page, Model model) {
        int pageNumIndex = Integer.parseInt(page) - 1;
        Pageable pageable = PageRequest.of(pageNumIndex, 10);
        Page<Teacher> teachers = teacherServiceImpl.findAllLimit(pageable);
        if (teachers != null) {
            List<Teacher> teachersList = teachers.toList();
            String json = convertTeachersListToJSON(teachersList);
            if (json != null) {
                return ResponseEntity.ok(json);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @ResponseBody
    @GetMapping(value = "api/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getStudents(
            @RequestParam(name = "page", defaultValue = "1") String page, Model model) {
        int pageNumIndex = Integer.parseInt(page) - 1;
        Pageable pageable = PageRequest.of(pageNumIndex, 10);
        Page<Student> students = studentServiceImpl.findAllLimit(pageable);
        if (students != null) {
            List<Student> studentsList = students.toList();
            String json = convertStudentsListToJSON(studentsList);
            if (json != null) {
                return ResponseEntity.ok(json);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @ResponseBody
    @GetMapping(value = "api/students/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getStudentsByGroup(@RequestParam(name = "group") String groupCode, Model model) {
        UniversityGroup group = groupServiceImpl.getByGroupCode(groupCode);
        if (group != null) {
            List<Student> studentsList = studentServiceImpl.getAllStudentsByGroup(group);
            if (studentsList != null && studentsList.size() > 0) {
                String json = convertStudentsListToJSON(studentsList);
                if (json != null) {
                    return ResponseEntity.ok(json);
                }
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @ResponseBody
    @GetMapping(value = "api/teachers/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getTeachersByNameAndLastname(@RequestParam(name = "name") String name, @RequestParam(name = "lastname") String lastname, Model model) {
        Teacher teacher = teacherServiceImpl.getByNameAndLastname(name, lastname);
        if (teacher != null) {
            String json = convertTeacherToJSON(teacher);
            if (json != null) {
                return ResponseEntity.ok(json);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    private String convertSubjectsListToJSON(List<Subject> subjectList) {
        return mapperWriteList(Subject.class, new SubjectSerializer(), subjectList);
    }

    private String convertStudentsListToJSON(List<Student> studentsList) {
        return mapperWriteList(Student.class, new StudentSerializer(), studentsList);
    }

    private String convertTeachersListToJSON(List<Teacher> teachersList) {
        return mapperWriteList(Teacher.class, new TeacherSerializer(), teachersList);
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

    private <T> String mapperWriteList(Class<? extends T> type, JsonSerializer<T> serializer, List<T> objects) {
        final ObjectMapper mapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(type, serializer);
        mapper.registerModule(simpleModule);
        try {
            return mapper.writeValueAsString(objects);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

}
