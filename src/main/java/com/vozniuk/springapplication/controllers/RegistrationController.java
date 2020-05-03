package com.vozniuk.springapplication.controllers;

import com.vozniuk.springapplication.domain.data.user.User;
import com.vozniuk.springapplication.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String signUpUser(User user, Model model) {

        if (!userService.addUser(user)) {
            model.addAttribute("msg", "Користувач з таким ім'ям вже існує!");
            return "registration";
        }

        return "redirect:/login";
    }
}
