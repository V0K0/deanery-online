package com.vozniuk.deanery.service.impl;

import com.vozniuk.deanery.domain.data.university.StudyingPlan;
import com.vozniuk.deanery.domain.data.university.Subject;
import com.vozniuk.deanery.repositories.SubjectRepository;
import com.vozniuk.deanery.service.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private SubjectRepository subjectRepository;

    @Autowired
    public void setSubjectRepository(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject addOrUpdateSubject(Subject subject) {
        subjectRepository.saveAndFlush(subject);
        return subject;
    }

    @Override
    public void deleteSubject(Subject subject) {
        subjectRepository.deleteById(subject.getSubjectId());
    }

    @Override
    public Subject getSubjectById(Integer id) {
        return subjectRepository.findById(id).isPresent() ? subjectRepository.findById(id).get() : null;
    }

    @Override
    public List<Subject> getAllByPlan(StudyingPlan plan) {
        return subjectRepository.findByPlanOrderBySubjectName(plan);
    }

    @Override
    public Subject getByNameAndPlan(String name, StudyingPlan plan) {
        return subjectRepository.findBySubjectNameAndPlan(name, plan).isPresent() ? subjectRepository.findBySubjectNameAndPlan(name, plan).get() : null;
    }

    @Override
    public Page<Subject> findAllLimit(Pageable pageable) {
        return subjectRepository.findAll(pageable);
    }

    @Override
    public Long getSubjectsCount() {
        return subjectRepository.getSubjectsCount();
    }

}
