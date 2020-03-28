package com.vozniuk.springapplication.repositories;

import com.vozniuk.springapplication.domain.data.university.UniversityGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<UniversityGroup, Integer> {

}
