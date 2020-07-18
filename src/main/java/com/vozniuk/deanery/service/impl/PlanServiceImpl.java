package com.vozniuk.deanery.service.impl;

import com.vozniuk.deanery.domain.data.university.StudyingPlan;
import com.vozniuk.deanery.repositories.PlanRepository;
import com.vozniuk.deanery.service.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl implements PlanService {

    private PlanRepository planRepository;

    @Autowired
    public void setPlanRepository(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

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
