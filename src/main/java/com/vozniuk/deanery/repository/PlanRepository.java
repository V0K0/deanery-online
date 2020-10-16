package com.vozniuk.deanery.repository;

import com.vozniuk.deanery.data.university.StudyingPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<StudyingPlan, Long> {

}
