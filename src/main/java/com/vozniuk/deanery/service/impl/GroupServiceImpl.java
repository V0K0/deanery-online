package com.vozniuk.deanery.service.impl;

import com.vozniuk.deanery.data.university.Specialty;
import com.vozniuk.deanery.data.university.UniversityGroup;
import com.vozniuk.deanery.repository.GroupRepository;
import com.vozniuk.deanery.service.GroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public UniversityGroup addOrUpdateGroup(UniversityGroup group) {
        return groupRepository.saveAndFlush(group);
    }

    @Override
    public void deleteGroup(UniversityGroup group) {
        groupRepository.delete(group);
    }

    @Override
    public UniversityGroup getGroupById(Long id) {
        return groupRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<UniversityGroup> getGroupsBySpecialty(Specialty specialty) {
        return groupRepository.findAllBySpecialty(specialty).orElse(new ArrayList<>());
    }

    @Override
    public UniversityGroup getByGroupCode(String groupCode) {
        return groupRepository.findByGroupCode(groupCode).orElseThrow(EntityNotFoundException::new);
    }
}
