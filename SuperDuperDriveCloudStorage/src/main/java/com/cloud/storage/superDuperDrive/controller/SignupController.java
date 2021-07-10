package com.cloud.storage.superDuperDrive.controller;

import com.cloud.storage.superDuperDrive.model.Users;
import com.cloud.storage.superDuperDrive.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("signup")
public class SignupController {

    private UsersService usersService;

    @Autowired
    public SignupController(UsersService usersService){
        this.usersService = usersService;
    }

    @GetMapping
    public String signup(Users users, Model model){
        return "signup";
    }

    @PostMapping()
    public String signupUser(@ModelAttribute Users user, Model model) {
        String signupError = null;

        if (!usersService.isUsernameAvailable(user.getUsername())) {
            signupError = "The username already exists.";
        }
        if (signupError == null) {
            int rowsAdded = usersService.createUser(user);
            if (rowsAdded < 0) {
                signupError = "There was an error signing you up. Please try again.";
            }
        }

        if (signupError == null) {
            model.addAttribute("signupSuccess", true);
            return "login";
        } else {
            model.addAttribute("signupError", signupError);
            return "signup";
        }
    }
}
