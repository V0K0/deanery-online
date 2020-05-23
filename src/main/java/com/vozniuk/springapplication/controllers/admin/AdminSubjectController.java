package com.vozniuk.springapplication.controllers.admin;

import com.vozniuk.springapplication.domain.data.university.StudyingPlan;
import com.vozniuk.springapplication.domain.data.university.Subject;
import com.vozniuk.springapplication.service.impl.PlanServiceImpl;
import com.vozniuk.springapplication.service.impl.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminSubjectController {

    @Autowired
    private SubjectServiceImpl subjectServiceImpl;

    @Autowired
    private PlanServiceImpl planServiceImpl;

    @DeleteMapping("/admin-page/study/subject/delete/{id}")
    @ResponseBody
    public String deleteSubject(@PathVariable("id") String id) {
        Subject subject = subjectServiceImpl.getSubjectById(Integer.parseInt(id));
        if (subject != null) {
            subjectServiceImpl.deleteSubject(subject);
            return "deleted";
        }
        return "failed";
    }

    @PostMapping("/admin-page/study/subject/create")
    public String createSubject(@RequestParam Map<String, String> allParams) {
        Subject newSubject = new Subject();
        fetchAndSetSubjectCreateAttributes(newSubject, allParams);
        if (newSubject.getPlan() != null) {
            subjectServiceImpl.addOrUpdateSubject(newSubject);
        }

        return "redirect:/admin-page/study";
    }


    @PutMapping("/admin-page/study/subject/update")
    @ResponseBody
    public String updateSubject(@RequestParam Map<String, String> allParams) {
        int id = Integer.parseInt(allParams.get("id"));
        Subject oldOneSubject = subjectServiceImpl.getSubjectById(id);
        if (oldOneSubject != null) {
            fetchAndSetSubjectUpdateAttributes(oldOneSubject, allParams);
            subjectServiceImpl.addOrUpdateSubject(oldOneSubject);
            return "success";
        }

       return "error";
    }

    private void fetchAndSetSubjectCreateAttributes(Subject subject, Map<String, String> attributes) {
        String subjectName = attributes.get("subjectName");
        int pHours = Integer.parseInt(attributes.get("practicalHours"));
        int lHours = Integer.parseInt(attributes.get("lectureHours"));
        String defenceType = attributes.get("defenceType");
        boolean courseWork = Boolean.parseBoolean(attributes.get("courseWork"));
        Date defenceDate;
        try {
            defenceDate = Date.valueOf(attributes.get("defenceDate"));
        } catch (Exception e) {
            defenceDate = null;
        }

        StudyingPlan plan = planServiceImpl.getPlanById(Integer.parseInt(attributes.get("plan")));
        setAttributes(subjectName, pHours, lHours, defenceType, courseWork, defenceDate, plan, subject);
    }

    private void fetchAndSetSubjectUpdateAttributes(Subject subject, Map<String, String> attributes) {
        String newSubjectName = attributes.get("name");
        int newPracticalHours = Integer.parseInt(attributes.get("ph"));
        int newLectureHours = Integer.parseInt(attributes.get("lh"));
        String newDefenceType = attributes.get("dType");
        boolean newCourseWork = Boolean.parseBoolean(attributes.get("cw"));
        Date newDefenceDate;
        try {
            newDefenceDate = Date.valueOf(attributes.get("date"));
        } catch (Exception e) {
            newDefenceDate = null;
        }
        setAttributes(newSubjectName, newPracticalHours, newLectureHours, newDefenceType, newCourseWork, newDefenceDate, null, subject);
    }

    private void setAttributes(String subjectName, int practicalHours, int lectureHours, String defenceType, boolean courseWork, Date defenceDate,
                               StudyingPlan studyingPlan, Subject subject) {
        if (!subjectName.isBlank()) {
            subject.setSubjectName(subjectName);
        }
        if (practicalHours > 0) {
            subject.setPracticalHours(practicalHours);
        }
        if (lectureHours > 0) {
            subject.setLectureHours(lectureHours);
        }
        if (!defenceType.isBlank()) {
            subject.setDefenceType(defenceType);
        }

        subject.setCourseWork(courseWork);

        if (defenceDate != null && defenceDate.compareTo(Date.valueOf(LocalDate.now())) >= 1) {
            subject.setDefenceDate(defenceDate);
        }

        if (studyingPlan != null) {
            subject.setPlan(studyingPlan);
        }
    }

}
