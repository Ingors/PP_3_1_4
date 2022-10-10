package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.PrivateUserDetailsService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private final UserServiceImpl userServiceImpl;
    private final PrivateUserDetailsService privateUserDetailsService;


    public UserController(UserServiceImpl userServiceImpl, PrivateUserDetailsService privateUserDetailsService) {
        this.userServiceImpl = userServiceImpl;
        this.privateUserDetailsService = privateUserDetailsService;
    }

    @GetMapping
    public String user(Model model) {
        String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = (User) privateUserDetailsService.loadUserByUsername(principalName);
        model.addAttribute("user", user);
        return "user";
    }
}