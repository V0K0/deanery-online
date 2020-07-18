package com.vozniuk.deanery.service.services;

import com.vozniuk.deanery.domain.data.university.StudyingPlan;

public interface PlanService {

    StudyingPlan addOrUpdatePlan(StudyingPlan plan);

    void deletePlan(StudyingPlan plan);

    StudyingPlan getPlanById(Integer id);

}
