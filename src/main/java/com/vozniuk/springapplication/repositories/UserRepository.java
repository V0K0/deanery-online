package com.vozniuk.springapplication.repositories;

import com.vozniuk.springapplication.domain.Role;
import com.vozniuk.springapplication.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
