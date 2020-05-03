package com.vozniuk.springapplication.service.impl;

import com.vozniuk.springapplication.domain.data.university.StudyingPlan;
import com.vozniuk.springapplication.domain.data.university.Subject;
import com.vozniuk.springapplication.repositories.SubjectRepository;
import com.vozniuk.springapplication.service.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

   @Autowired
    private SubjectRepository subjectRepository;


    @Override
    public Subject addSubject(Subject subject) {
        subjectRepository.save(subject);
        return subject;
    }

    @Override
    public void deleteSubject(Subject subject) {
        subjectRepository.delete(subject);
    }

    @Override
    public Subject getSubjectById(Integer id) {
        return subjectRepository.findById(id).isPresent() ? subjectRepository.findById(id).get() : null;
    }

    @Override
    public Subject editSubject(Subject subject) {
        subjectRepository.saveAndFlush(subject);
        return subject;
    }

    @Override
    public List<Subject> getAllByPlan(StudyingPlan plan) {
       return subjectRepository.findByPlanOrderBySubjectName(plan);
    }
}
