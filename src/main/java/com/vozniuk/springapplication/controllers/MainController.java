package com.vozniuk.springapplication.controllers;

import com.vozniuk.springapplication.domain.data.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String startPage(){
        return "startPage";
    }

    @GetMapping("/home")
    public String homePage(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("username", user.getUsername());
        return "home";
    }
}
