package com.vozniuk.deanery.service;

import com.vozniuk.deanery.data.university.Student;
import com.vozniuk.deanery.data.user.User;

public interface UserService {

    Student saveUserAsStudent(User user);

    User saveUserAsAdmin(User user);

    void deleteUser(User user);

}
