package com.vozniuk.deanery.web.controllers.user;

import com.vozniuk.deanery.domain.data.user.User;
import com.vozniuk.deanery.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Locale;
import java.util.ResourceBundle;

@Controller
public class RegistrationController {

    private UserServiceImpl userServiceImpl;

    private Locale locale;

    private final Logger logger = LogManager.getLogger(RegistrationController.class);

    @Autowired
    public void setLocale(Locale locale){
        this.locale = locale;
    }

    @Autowired
    public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String signUpUser(User user, Model model) {

        boolean registrationSucceed = userServiceImpl.addUser(user);

        if (!registrationSucceed) {

            logger.trace("Failed registration attempt. Already existent username");

            final ResourceBundle messages = ResourceBundle.getBundle("messages", locale);
            model.addAttribute("msgError", messages.getString("msgErrorAlreadyExists"));
            return "registration";
        }

        userServiceImpl.saveUserAsStudent(user);

        logger.info("Successfully created new user with name: {}", user.getUsername());

        return "redirect:/login";
    }
}
