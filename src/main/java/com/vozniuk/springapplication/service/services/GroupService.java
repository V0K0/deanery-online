package com.vozniuk.springapplication.service.services;

import com.vozniuk.springapplication.domain.data.university.Specialty;
import com.vozniuk.springapplication.domain.data.university.UniversityGroup;

import java.util.List;

public interface GroupService {

    UniversityGroup addGroup(UniversityGroup group);

    void deleteGroup(UniversityGroup group);

    UniversityGroup getGroupById(Integer id);

    UniversityGroup editGroup(UniversityGroup group);

    List<UniversityGroup> getGroupsBySpecialty(Specialty specialty);

    UniversityGroup getByGroupCode(String groupCode);

}
