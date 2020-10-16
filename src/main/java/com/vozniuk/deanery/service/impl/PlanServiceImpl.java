package com.vozniuk.deanery.service.impl;

import com.vozniuk.deanery.data.university.StudyingPlan;
import com.vozniuk.deanery.repository.PlanRepository;
import com.vozniuk.deanery.service.PlanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    public PlanServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public StudyingPlan addOrUpdatePlan(StudyingPlan plan) {
        return planRepository.saveAndFlush(plan);
    }

    @Override
    public void deletePlan(StudyingPlan plan) {
        planRepository.delete(plan);
    }

    @Override
    public StudyingPlan getPlanById(Long id) {
        return planRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
