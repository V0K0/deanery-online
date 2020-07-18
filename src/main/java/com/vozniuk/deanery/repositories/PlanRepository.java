package com.vozniuk.deanery.repositories;

import com.vozniuk.deanery.domain.data.university.StudyingPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<StudyingPlan, Integer> {

}
