package com.vozniuk.deanery.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vozniuk.deanery.data.university.StudyingPlan;
import com.vozniuk.deanery.data.university.Subject;
import com.vozniuk.deanery.json.converter.CollectionToJSONConverter;
import com.vozniuk.deanery.json.serializer.SubjectSerializer;
import com.vozniuk.deanery.service.PlanService;
import com.vozniuk.deanery.service.SubjectService;
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
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SubjectController {

    private final SubjectService subjectService;
    private final PlanService planService;

    private final Logger logger = LogManager.getLogger(SubjectController.class);

    @Autowired
    public SubjectController(SubjectService subjectService, PlanService planService) {
        this.subjectService = subjectService;
        this.planService = planService;
    }

    @GetMapping(value = "/subjects", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getSubjects(
            @RequestParam(name = "page", defaultValue = "1") String page) {
        try {
            int pageNumIndex = Integer.parseInt(page) - 1;
            Pageable pageable = PageRequest.of(pageNumIndex, 10);
            Page<Subject> subjects = subjectService.findAllLimit(pageable);
            List<Subject> subjectList = subjects.toList();
            String json = CollectionToJSONConverter.writeAsJSON(Subject.class, new SubjectSerializer(), subjectList);
            return ResponseEntity.ok(json);
        } catch (JsonProcessingException e) {
            logger.error("Unable to convert collection of subjects to json!");
        } catch (Exception e) {
            logger.error("Unable to retrieve collection of subjects cause: {}", e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping(value = "/subjects/plans/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getSubjectsByPlan(@PathVariable(name = "id") long planId) {
        try {
            StudyingPlan studyingPlan = planService.getPlanById(planId);
            if (studyingPlan != null) {
                List<Subject> subjectList = subjectService.getAllByPlan(studyingPlan);
                String json = CollectionToJSONConverter.writeAsJSON(Subject.class, new SubjectSerializer(), subjectList);
                if (json != null) {
                    return ResponseEntity.ok(json);
                }
            }
        } catch (EntityNotFoundException e) {
            logger.error("Failed to get subjects by plan id: plan with such id not exists!");
        } catch (Exception e) {
            logger.error("Failed to get list of subjects by plan cause: {}", e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/subjects/{id}")
    @ResponseBody
    public Object deleteSubject(@PathVariable("id") String id) {
        try {
            Subject subject = subjectService.getSubjectById(Long.parseLong(id));
            if (subject != null) {
                subjectService.deleteSubject(subject);
                logger.info("Successfully removed subject with id: {}", id);
                return ResponseEntity.status(HttpStatus.OK).body("Deleted");
            }
        } catch (EntityNotFoundException e) {
            logger.error("Failed to remove entity cause: no such entity was found!");
        } catch (Exception e) {
            logger.error("Failed to remove subject with id: {}, cause {}", id, e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Denied");
    }


    @PutMapping("/subjects/{id}")
    @ResponseBody
    public Object updateSubject(@PathVariable(name = "id") long id, @RequestParam Map<String, String> allParams) {
        try {
            Subject subject = subjectService.getSubjectById(id);
            if (subject != null) {
                subject.setSubjectName(allParams.getOrDefault("name", subject.getSubjectName()));
                subject.setPracticalHours(Integer.valueOf(allParams.getOrDefault("ph", subject.getPracticalHours().toString())));
                subject.setLectureHours(Integer.valueOf(allParams.getOrDefault("lh", subject.getLectureHours().toString())));
                subject.setDefenceType(allParams.getOrDefault("dType", subject.getDefenceType()));
                subject.setCourseWork(Boolean.parseBoolean(allParams.getOrDefault("cw", "false")));
                String stringDate = allParams.getOrDefault("defenceDate", null);
                if (stringDate != null && Date.valueOf(stringDate).compareTo(Date.valueOf(LocalDate.now())) > 0) {
                    subject.setDefenceDate(Date.valueOf(stringDate));
                }
                subjectService.addOrUpdateSubject(subject);
                logger.info("Successfully updated subject with id: {}", id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Updated");
            }
        } catch (EntityNotFoundException e) {
            logger.error("Failed to update subject cause: subject with such id not exists!");
        } catch (Exception e) {
            logger.error("Failed to update subject with id: {}, cause: {}", id, e.getCause());
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Denied");
    }


}
