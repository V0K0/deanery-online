package com.vozniuk.deanery.repositories;

import com.vozniuk.deanery.domain.data.university.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findByLastnameAndName(String lastname, String name);

    @Procedure("count_teachers")
    Long getTeachersCount();
}
