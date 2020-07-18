package com.vozniuk.deanery.controllers.user;

import com.vozniuk.deanery.config.WebConfig;
import com.vozniuk.deanery.domain.data.user.User;
import com.vozniuk.deanery.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ResourceBundle;

@Controller
public class RegistrationController {

    private UserServiceImpl userServiceImpl;

    private WebConfig webConfig;

    @Autowired
    public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Autowired
    public void setWebConfig(WebConfig webConfig) {
        this.webConfig = webConfig;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String signUpUser(User user, Model model) {

        if (!userServiceImpl.addUser(user)) {
            final ResourceBundle messages = ResourceBundle.getBundle("messages", webConfig.locale());
            model.addAttribute("msgError", messages.getString("msgErrorAlreadyExists"));
            return "registration";
        }

        return "redirect:/login";
    }
}
