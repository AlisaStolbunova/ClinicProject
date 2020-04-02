package com.clinic.controllers;

import com.clinic.domain.User;
import com.clinic.dto.UserDto;
import com.clinic.services.ClinicUserDetailService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Locale;

@Controller
public class UserController {

    @Autowired
    private ClinicUserDetailService userDetailService;
    @Autowired
    private MessageSource messageSource;


    @GetMapping("/signup")
    public String get(){
        return "signup";
    }

    @PostMapping("/signup")
    public String create(Model model, UserDto user){
        try{
            userDetailService.create(user);
        } catch (Exception ex){
            model.addAttribute("errorMessage",
                    messageSource.getMessage("user.exist", new Object[]{user.getName()}, Locale.getDefault()));
            return "signup";
        }
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
