package com.vozniuk.deanery.service;

import com.vozniuk.deanery.data.university.StudyingPlan;

public interface PlanService {

    StudyingPlan addOrUpdatePlan(StudyingPlan plan);

    void deletePlan(StudyingPlan plan);

    StudyingPlan getPlanById(Long id);

}
