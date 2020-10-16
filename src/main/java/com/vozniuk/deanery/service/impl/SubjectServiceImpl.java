package com.vozniuk.deanery.service.impl;

import com.vozniuk.deanery.data.university.StudyingPlan;
import com.vozniuk.deanery.data.university.Subject;
import com.vozniuk.deanery.repository.SubjectRepository;
import com.vozniuk.deanery.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject addOrUpdateSubject(Subject subject) {
        return subjectRepository.saveAndFlush(subject);
    }

    @Override
    public void deleteSubject(Subject subject) {
        subjectRepository.delete(subject);
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Subject> getAllByPlan(StudyingPlan plan) {
        return subjectRepository.findByPlanOrderBySubjectName(plan);
    }

    @Override
    public Subject getByNameAndPlan(String name, StudyingPlan plan) {
        return subjectRepository.findBySubjectNameAndPlan(name, plan).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<Subject> findAllLimit(Pageable pageable) {
        return subjectRepository.findAll(pageable);
    }

    @Override
    public Long getSubjectsCount() {
        return subjectRepository.count();
    }
}
