package com.vozniuk.springapplication.service.services;

import com.vozniuk.springapplication.domain.data.university.StudyingPlan;

public interface PlanService {

    StudyingPlan addOrUpdatePlan(StudyingPlan plan);

    void deletePlan(StudyingPlan plan);

    StudyingPlan getPlanById(Integer id);

}
