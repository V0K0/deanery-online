package com.vozniuk.deanery.service.impl;

import com.vozniuk.deanery.data.university.Student;
import com.vozniuk.deanery.data.user.Role;
import com.vozniuk.deanery.data.user.User;
import com.vozniuk.deanery.repository.UserRepository;
import com.vozniuk.deanery.service.StudentService;
import com.vozniuk.deanery.service.UserService;
import com.vozniuk.deanery.service.exception.UserCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;

    private final StudentService studentService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(8);

    @Autowired
    public UserServiceImpl(UserRepository userRepository, StudentService studentService) {
        this.userRepository = userRepository;
        this.studentService = studentService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " was not found!");
        }
        return user;
    }

    @Override
    public Student saveUserAsStudent(User user) {
        saveDefaultUser(user);
        Student student = new Student(user.getId());
        student.setAccount(user);
        studentService.addOrUpdateStudent(student);
        return student;
    }

    @Override
    public User saveUserAsAdmin(User user) {
        return saveAdminUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    private void saveDefaultUser(User user) {
        if (userExist(user)) {
            throw new UserCreationException("Can`t save user. User already exists!");
        }
        setDefaultUserProperties(user);
        userRepository.saveAndFlush(user);
    }

    private User saveAdminUser(User user) {
        if (userExist(user)) {
            throw new UserCreationException("Can`t save admin. Admin with such username already exists!");
        }
        setDefaultUserProperties(user);
        user.getRoles().add(Role.ADMIN);
        return userRepository.saveAndFlush(user);
    }

    private void setDefaultUserProperties(User user) {
        user.setActive(true);
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    private boolean userExist(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        return userFromDB != null;
    }


    @Autowired
    public void setEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
