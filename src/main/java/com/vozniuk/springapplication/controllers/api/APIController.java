package com.vozniuk.springapplication.controllers.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.vozniuk.springapplication.domain.data.university.Student;
import com.vozniuk.springapplication.domain.data.university.UniversityGroup;
import com.vozniuk.springapplication.json.StudentSerializer;
import com.vozniuk.springapplication.json.SubjectSerializer;
import com.vozniuk.springapplication.domain.data.university.Subject;
import com.vozniuk.springapplication.service.impl.GroupServiceImpl;
import com.vozniuk.springapplication.service.impl.StudentServiceImpl;
import com.vozniuk.springapplication.service.impl.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
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

    @ResponseBody
    @GetMapping(value = "api/subject", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getSubjects(
            @RequestParam(name = "page", defaultValue = "1") String page, Model model) {
        return getAllSubjectsJSONAsString(page);
    }

    @ResponseBody
    @GetMapping(value = "api/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getStudents(
            @RequestParam(name = "page", defaultValue = "1") String page, Model model) {
        return getAllStudentsJSON(page);
    }

    @ResponseBody
    @GetMapping(value = "api/students/byGroup", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getStudentsByGroup(@RequestParam(name = "group") String groupCode, Model model) {
        UniversityGroup group = groupServiceImpl.getByGroupCode(groupCode);
        if (group != null){
            return getAllStudentsByGroupJSON(group);
        }
        return "failed";
    }


    private String getAllSubjectsJSONAsString(String page) {
        int pageNumIndex = Integer.parseInt(page) - 1;
        Pageable pageable = PageRequest.of(pageNumIndex, 10);
        Page<Subject> subjects = subjectServiceImpl.findAllLimit(pageable);
        List<Subject> subjectList = subjects.toList();
        final ObjectMapper mapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Subject.class, new SubjectSerializer());
        mapper.registerModule(simpleModule);
        try {
            return mapper.writeValueAsString(subjectList);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    private String getAllStudentsByGroupJSON(UniversityGroup group) {
        List<Student> studentsList = studentServiceImpl.getAllStudentsByGroup(group);
        final ObjectMapper mapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Student.class, new StudentSerializer());
        mapper.registerModule(simpleModule);
        try {
            return mapper.writeValueAsString(studentsList);
        } catch (JsonProcessingException e) {
            return null;
        }
    }


    private String getAllStudentsJSON(String page) {
        int pageNumIndex = Integer.parseInt(page) - 1;
        Pageable pageable = PageRequest.of(pageNumIndex, 10);
        Page<Student> students = studentServiceImpl.findAllLimit(pageable);
        List<Student> studentsList = students.toList();
        final ObjectMapper mapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Student.class, new StudentSerializer());
        mapper.registerModule(simpleModule);
        try {
            return mapper.writeValueAsString(studentsList);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

}
