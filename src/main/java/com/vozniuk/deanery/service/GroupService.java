package com.vozniuk.deanery.service;

import com.vozniuk.deanery.data.university.Specialty;
import com.vozniuk.deanery.data.university.UniversityGroup;

import java.util.List;

public interface GroupService {

    UniversityGroup addOrUpdateGroup(UniversityGroup group);

    void deleteGroup(UniversityGroup group);

    UniversityGroup getGroupById(Long id);

    List<UniversityGroup> getGroupsBySpecialty(Specialty specialty);

    UniversityGroup getByGroupCode(String groupCode);

}
