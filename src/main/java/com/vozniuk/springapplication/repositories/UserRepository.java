package com.vozniuk.springapplication.repositories;

import com.vozniuk.springapplication.domain.data.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
