package com.clinic.controllers;

import com.clinic.domain.User;
import com.clinic.dto.UserDto;
import com.clinic.services.ClinicUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private ClinicUserDetailService userDetailService;


    @GetMapping("/signup")
    public String get(){
        return "signup";
    }

    @PostMapping("/signup")
    public String create(UserDto user){
        userDetailService.create(user);
        return "index";
    }

    @GetMapping("/user/all")
    public String findAll(Model model){
        Iterable<User> users = userDetailService.findAll();
        users.forEach(user -> user.setPassword(null));
        model.addAttribute("users", users);
        return "user";
    }

    @GetMapping("/account")
    public String account(Model model, Authentication authentication){
        User user = userDetailService.findUserByNameWithTickets(authentication.getName());
        user.setPassword(null);
        model.addAttribute("user", user);
        return "account";
    }


}
