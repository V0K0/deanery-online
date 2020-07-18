package com.vozniuk.deanery.repositories;

import com.vozniuk.deanery.domain.data.university.Specialty;
import com.vozniuk.deanery.domain.data.university.UniversityGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<UniversityGroup, Integer> {
    UniversityGroup findByGroupCode(String groupCode);
    List<UniversityGroup> findAllBySpecialty(Specialty specialty);
}
