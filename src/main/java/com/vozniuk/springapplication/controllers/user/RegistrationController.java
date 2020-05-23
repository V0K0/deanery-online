package com.vozniuk.springapplication.controllers.user;

import com.vozniuk.springapplication.config.WebConfig;
import com.vozniuk.springapplication.domain.data.user.User;
import com.vozniuk.springapplication.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ResourceBundle;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private WebConfig webConfig;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String signUpUser(User user, Model model) {

        if (!userService.addUser(user)) {
            final ResourceBundle messages = ResourceBundle.getBundle("messages", webConfig.locale());
            model.addAttribute("msgError", messages.getString("msgErrorAlreadyExists"));
            return "registration";
        }

        return "redirect:/login";
    }
}
