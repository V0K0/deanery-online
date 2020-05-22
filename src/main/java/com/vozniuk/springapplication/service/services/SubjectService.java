package com.vozniuk.springapplication.service.services;

import com.vozniuk.springapplication.domain.data.university.StudyingPlan;
import com.vozniuk.springapplication.domain.data.university.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubjectService {

    Subject addOrUpdateSubject(Subject subject);

    void deleteSubject(Subject subject);

    Subject getSubjectById(Integer id);

    List<Subject> getAllByPlan(StudyingPlan plan);

    Page<Subject> findAllLimit(Pageable pageable);

}
