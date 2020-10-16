package com.vozniuk.deanery.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vozniuk.deanery.data.university.Student;
import com.vozniuk.deanery.data.university.UniversityGroup;
import com.vozniuk.deanery.json.converter.CollectionToJSONConverter;
import com.vozniuk.deanery.json.serializer.StudentSerializer;
import com.vozniuk.deanery.service.GroupService;
import com.vozniuk.deanery.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentService studentService;
    private final GroupService groupService;

    private final Logger logger = LogManager.getLogger(StudentController.class);

    @Autowired
    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getStudents(@RequestParam(name = "page", defaultValue = "1") String page) {
        try {
            int pageIndex = Integer.parseInt(page) - 1;
            Pageable pageable = PageRequest.of(pageIndex, 10);
            Page<Student> students = studentService.findAllLimit(pageable);
            List<Student> studentsList = students.toList();
            String json = CollectionToJSONConverter.writeAsJSON(Student.class, new StudentSerializer(), studentsList);
            return ResponseEntity.ok(json);
        } catch (JsonProcessingException e) {
            logger.error("Unable to convert collection of students to json!");
        } catch (Exception e) {
            logger.error("Unable to retrieve collection of students cause: {}", e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping(value = "/students/groups", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getStudentsByGroup(@RequestParam(name = "group") String groupCode) {
        try {
            UniversityGroup group = groupService.getByGroupCode(groupCode);
            if (group != null) {
                List<Student> studentsList = studentService.getAllStudentsByGroup(group);
                if (!studentsList.isEmpty()) {
                    String json = CollectionToJSONConverter.writeAsJSON(Student.class, new StudentSerializer(), studentsList);
                    return ResponseEntity.ok(json);
                }
            }
        } catch (EntityNotFoundException e) {
            logger.error("Unable to find students by group with code: {}", groupCode);
        } catch (JsonProcessingException e) {
            logger.error("Unable to convert collection of students to json!");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/students/{id}")
    public Object updateStudent(@PathVariable(name = "id") long id, @RequestParam Map<String, String> allParams) {
        try {
            Student student = studentService.getStudentById(id);
            if (student != null) {
                student.setName(allParams.getOrDefault("name", student.getName()));
                student.setLastname(allParams.getOrDefault("lastname", student.getName()));
                String groupCode = allParams.get("stGroup");
                student.setStudentGroup(groupCode != null ? groupService.getByGroupCode(groupCode) : student.getStudentGroup());
                studentService.addOrUpdateStudent(student);
                logger.info("Successfully updated student with id {}", id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Updated");
            }
        } catch (EntityNotFoundException e) {
            logger.error("Unable to find student with id {}", id);
        } catch (Exception e) {
            logger.error("Failed to update student with id {}", id);
        }

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Denied");
    }

}
