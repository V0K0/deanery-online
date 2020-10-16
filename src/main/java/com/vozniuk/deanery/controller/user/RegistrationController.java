package com.vozniuk.deanery.controller.user;

import com.vozniuk.deanery.data.user.User;
import com.vozniuk.deanery.service.UserService;
import com.vozniuk.deanery.service.exception.UserCreationException;
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

    private final Logger logger = LogManager.getLogger(RegistrationController.class);
    private final UserService userService;
    private Locale locale;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
        locale = Locale.forLanguageTag("en-US");
    }

    @Autowired
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String signUpUser(User user, Model model) {
        try {
            userService.saveUserAsStudent(user);
        } catch (UserCreationException exception) {
            logger.error(exception.getMessage());
            final ResourceBundle messages = ResourceBundle.getBundle("messages", locale);
            model.addAttribute("msgError", messages.getString("msgErrorAlreadyExists"));
            return "registration";
        }
        logger.info("Successfully created new user with name: {}", user.getUsername());
        return "redirect:/login";
    }
}
