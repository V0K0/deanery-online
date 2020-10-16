package com.vozniuk.deanery.controller.user;

import com.vozniuk.deanery.data.user.Role;
import com.vozniuk.deanery.data.user.User;
import com.vozniuk.deanery.ui.model.StudentModelLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {

    private final Logger logger = LogManager.getLogger(StartController.class);

    private final StudentModelLoader studentModelLoader;

    public StartController(StudentModelLoader studentModelLoader) {
        this.studentModelLoader = studentModelLoader;
    }

    @GetMapping("/")
    public String startPage() {
        return "startPage";
    }

    @GetMapping("/home")
    public String homePage(@AuthenticationPrincipal User user, Model model) {

        String username = user.getUsername();
        model.addAttribute("username", username);

        if (user.getAuthorities().contains(Role.ADMIN)) {
            logger.info("Admin: {} logged in", username);
            return "forward:admin-page";
        }

        studentModelLoader.load(user, model);

        logger.info("User: {} logged in", username);

        return "home";
    }

}
