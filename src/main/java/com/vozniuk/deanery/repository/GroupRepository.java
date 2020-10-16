package com.vozniuk.deanery.repository;

import com.vozniuk.deanery.data.university.Specialty;
import com.vozniuk.deanery.data.university.UniversityGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<UniversityGroup, Long> {

    @Query("select gr from UniversityGroup gr left join fetch gr.students where gr.groupCode = :groupCode")
    Optional<UniversityGroup> findByGroupCode(String groupCode);

    Optional<List<UniversityGroup>> findAllBySpecialty(Specialty specialty);

}
