package com.vozniuk.deanery.repositories;

import com.vozniuk.deanery.domain.data.university.Student;
import com.vozniuk.deanery.domain.data.university.UniversityGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findAllByStudentGroup(UniversityGroup group);

    @Procedure("count_students")
    Long getStudentsCount();
}
