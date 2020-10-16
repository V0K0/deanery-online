package com.vozniuk.deanery.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vozniuk.deanery.data.university.Teacher;
import com.vozniuk.deanery.json.converter.CollectionToJSONConverter;
import com.vozniuk.deanery.json.converter.TeacherToJSONConverter;
import com.vozniuk.deanery.json.serializer.TeacherSerializer;
import com.vozniuk.deanery.service.TeacherService;
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
public class TeacherController {

    private final TeacherService teacherService;

    private final Logger logger = LogManager.getLogger(TeacherController.class);

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping(value = "/teachers", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getTeachers(@RequestParam(name = "page", defaultValue = "1") String page) {
        try {
            int pageIndex = Integer.parseInt(page) - 1;
            Pageable pageable = PageRequest.of(pageIndex, 10);
            Page<Teacher> teachers = teacherService.findAllLimit(pageable);
            if (teachers != null) {
                List<Teacher> teachersList = teachers.toList();
                String json = CollectionToJSONConverter.writeAsJSON(Teacher.class, new TeacherSerializer(), teachersList);
                return ResponseEntity.ok(json);
            }
        } catch (JsonProcessingException e) {
            logger.error("Unable to convert collection of teachers to json!");
        } catch (Exception e) {
            logger.error("Unable to retrieve collection of teachers cause: {}", e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


    @GetMapping(value = "/teachers/special", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getTeachersByNameAndLastname(@RequestParam(name = "name") String name, @RequestParam(name = "lastname") String lastname) {
        Teacher teacher = null;
        try {
            teacher = teacherService.getByNameAndLastname(name, lastname);
            if (teacher != null) {
                String json = TeacherToJSONConverter.convertTeacherToJSON(teacher);
                return ResponseEntity.ok(json);
            }
        } catch (EntityNotFoundException e) {
            logger.error("Unable to find teacher with such name and lastname!");
        } catch (JsonProcessingException e) {
            logger.error("Unable to convert teacher with id: {} to json!", teacher.getTeacherId());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/teachers/{id}")
    @ResponseBody
    public Object updateTeacher(@PathVariable(name = "id") long id, @RequestParam Map<String, String> allParams) {
        try {
            Teacher teacher = teacherService.getTeacherById(id);
            if (teacher != null) {
                teacher.setName(allParams.getOrDefault("name", teacher.getName()));
                teacher.setLastname(allParams.getOrDefault("lastname", teacher.getLastname()));
                teacher.setPatronymic(allParams.getOrDefault("patron", teacher.getPatronymic()));
                String phone = allParams.get("phone");
                teacher.setPhone(phone.isBlank() ? teacher.getPhone() : phone.replace("-", ""));
                teacherService.addOrUpdateTeacher(teacher);
                logger.info("Teacher with id: {} was updated", teacher.getTeacherId());
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Updated");
            }
        } catch (EntityNotFoundException e) {
            logger.error("Failed update teacher cause: teacher with such id not exists!");
        } catch (Exception e) {
            logger.error("Failed to update teacher cause: {}", e.toString());
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED);
    }

}
