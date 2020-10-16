package com.vozniuk.deanery.repository;

import com.vozniuk.deanery.data.university.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByLastnameAndName(String lastname, String name);

}
