package com.vozniuk.springapplication.repositories;

import com.vozniuk.springapplication.domain.data.university.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
