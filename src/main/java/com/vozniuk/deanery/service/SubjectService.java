package com.vozniuk.deanery.service;

import com.vozniuk.deanery.data.university.StudyingPlan;
import com.vozniuk.deanery.data.university.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubjectService {

    Subject addOrUpdateSubject(Subject subject);

    void deleteSubject(Subject subject);

    Subject getSubjectById(Long id);

    List<Subject> getAllByPlan(StudyingPlan plan);

    Subject getByNameAndPlan(String name, StudyingPlan plan);

    Page<Subject> findAllLimit(Pageable pageable);

    Long getSubjectsCount();

}
