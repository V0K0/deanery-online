package com.vozniuk.deanery.controller.admin;

import com.vozniuk.deanery.data.university.StudyingPlan;
import com.vozniuk.deanery.data.university.Subject;
import com.vozniuk.deanery.service.PlanService;
import com.vozniuk.deanery.service.SubjectService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin-page")
public class AdminSubjectController {

    private final Logger logger = LogManager.getLogger(AdminSubjectController.class);
    private final SubjectService subjectService;
    private final PlanService planService;

    @Autowired
    public AdminSubjectController(SubjectService subjectService, PlanService planService) {
        this.subjectService = subjectService;
        this.planService = planService;
    }

    @GetMapping(value = "/study")
    public String studyPage(Model model) {
        Long countOfSubject = subjectService.getSubjectsCount();
        model.addAttribute("countOfSubjects", countOfSubject);
        return "admin-study-page";
    }

    @PostMapping("/study/subjects/")
    public String createSubject(@RequestParam Map<String, String> allParams) {
        try {
            Subject subject = Subject.builder()
                    .subjectName(allParams.get("subjectName"))
                    .practicalHours(Integer.parseInt(allParams.getOrDefault("practicalHours", "1")))
                    .lectureHours(Integer.parseInt(allParams.getOrDefault("lectureHours", "1")))
                    .courseWork(Boolean.parseBoolean(allParams.getOrDefault("courseWork", "false")))
                    .defenceType(allParams.get("defenceType"))
                    .build();

            String stringDate = allParams.get("defenceDate");
            if (!stringDate.isBlank() && Date.valueOf(stringDate).compareTo(Date.valueOf(LocalDate.now())) > 0) {
                subject.setDefenceDate(Date.valueOf(stringDate));
            }

            StudyingPlan plan = planService.getPlanById(Long.parseLong(allParams.get("plan")));
            subject.setPlan(plan);

            subjectService.addOrUpdateSubject(subject);

            logger.info("Successfully added new subject with id {}", subject.getSubjectId());
        } catch (EntityNotFoundException e) {
            logger.error("Failed to add new subject with cause: plan with such id not exists!");
        } catch (Exception e) {
            logger.error("Failed to create new subject with cause: {}", e.getMessage());
        }

        return "redirect:/admin-page/study";
    }

}
