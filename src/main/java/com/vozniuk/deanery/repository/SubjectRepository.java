package com.vozniuk.deanery.repository;

import com.vozniuk.deanery.data.university.StudyingPlan;
import com.vozniuk.deanery.data.university.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findByPlanOrderBySubjectName(StudyingPlan plan);

    Optional<Subject> findBySubjectNameAndPlan(String subjectName, StudyingPlan plan);

}
