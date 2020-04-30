package com.vozniuk.springapplication.service.impl;

import com.vozniuk.springapplication.domain.data.university.Specialty;
import com.vozniuk.springapplication.domain.data.university.UniversityGroup;
import com.vozniuk.springapplication.repositories.GroupRepository;
import com.vozniuk.springapplication.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public UniversityGroup addGroup(UniversityGroup group) {
        groupRepository.save(group);
        return group;
    }

    @Override
    public void deleteGroup(UniversityGroup group) {
        groupRepository.delete(group);
    }

    @Override
    public UniversityGroup getGroupById(Integer id) {
        return groupRepository.findById(id).isPresent() ?  groupRepository.findById(id).get() : null;
    }

    @Override
    public UniversityGroup editGroup(UniversityGroup group) {
       groupRepository.saveAndFlush(group);
       return group;
    }

    @Override
    public List<UniversityGroup> getGroupsBySpecialty(Specialty specialty) {
        return groupRepository.findAllBySpecialty(specialty);
    }

    @Override
    public UniversityGroup getByGroupCode(String groupCode) {
        return groupRepository.findByGroupCode(groupCode);
    }
}