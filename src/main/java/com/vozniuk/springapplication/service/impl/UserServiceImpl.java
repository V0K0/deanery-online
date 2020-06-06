package com.vozniuk.springapplication.service.impl;

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
public class UserServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    private StudentServiceImpl studentServiceImpl;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setStudentServiceImpl(StudentServiceImpl studentServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

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
        studentServiceImpl.addOrUpdateStudent(student);
    }

}
