package com.vozniuk.springapplication.repositories;

import com.vozniuk.springapplication.domain.data.university.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findByLastnameAndName(String lastname, String name);
}
