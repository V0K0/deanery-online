package com.vozniuk.springapplication.service.impl;

import com.vozniuk.springapplication.domain.data.university.Schedule;
import com.vozniuk.springapplication.domain.data.university.StudyingPlan;
import com.vozniuk.springapplication.repositories.PlanRepository;
import com.vozniuk.springapplication.service.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository planRepository;

    @Override
    public StudyingPlan addOrUpdatePlan(StudyingPlan plan) {
        planRepository.saveAndFlush(plan);
        return plan;
    }

    @Override
    public void deletePlan(StudyingPlan plan) {
        planRepository.deleteById(plan.getPlanId());
    }

    @Override
    public StudyingPlan getPlanById(Integer id) {
        return planRepository.findById(id).isPresent() ?  planRepository.findById(id).get() : null;
    }

}
