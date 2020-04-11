package com.vozniuk.springapplication.repositories;

import com.vozniuk.springapplication.domain.data.university.Specialty;
import com.vozniuk.springapplication.domain.data.university.UniversityGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<UniversityGroup, Integer> {
    UniversityGroup findByGroupCode(String groupCode);
    List<UniversityGroup> findAllBySpecialty(Specialty specialty);
}
