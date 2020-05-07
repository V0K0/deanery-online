package com.vozniuk.springapplication.repositories;

import com.vozniuk.springapplication.domain.data.university.StudyingPlan;
import com.vozniuk.springapplication.domain.data.university.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    List<Subject> findByPlanOrderBySubjectName(StudyingPlan plan);
}
