package com.vozniuk.springapplication.service.services;

import com.vozniuk.springapplication.domain.data.university.Schedule;
import com.vozniuk.springapplication.domain.data.university.StudyingPlan;
import com.vozniuk.springapplication.domain.data.university.Subject;
import com.vozniuk.springapplication.domain.data.university.UniversityGroup;

import java.util.List;

public interface SubjectService {

    Subject addSubject(Subject subject);

    void deleteSubject(Subject subject);

    Subject getSubjectById(Integer id);

    Subject editSubject(Subject subject);

    List<Subject> getAllByPlan(StudyingPlan plan);

}
