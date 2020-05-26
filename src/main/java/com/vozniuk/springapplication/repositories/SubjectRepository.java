package com.vozniuk.springapplication.repositories;

import com.vozniuk.springapplication.domain.data.university.StudyingPlan;
import com.vozniuk.springapplication.domain.data.university.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    List<Subject> findByPlanOrderBySubjectName(StudyingPlan plan);

    @Procedure("count_subjects")
    Long getSubjectsCount();

    Optional<Subject> findBySubjectNameAndPlan(String subjectName, StudyingPlan plan);

}
