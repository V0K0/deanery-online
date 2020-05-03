package com.vozniuk.springapplication.service.services;

import com.vozniuk.springapplication.domain.data.university.Student;
import com.vozniuk.springapplication.domain.data.user.Role;
import com.vozniuk.springapplication.domain.data.user.User;
import com.vozniuk.springapplication.repositories.UserRepository;
import com.vozniuk.springapplication.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    StudentServiceImpl studentServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public boolean addUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        saveUserAsStudent(user);

        return true;
    }


    private void saveUserAsStudent(User user) {
        Student student = new Student();
        student.setId(user.getId());
        studentServiceImpl.addStudent(student);
    }
}
