package com.example.donaresange.controller;

import com.example.donaresange.model.User;
import com.example.donaresange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user/register")
    public Boolean register(@RequestParam() String email, @RequestParam() String password, @RequestParam() String name, @RequestParam() String bloodType, @RequestParam() String address) {
        return userService.register(email, password, name, bloodType, address);
    }

    @GetMapping("/user/login")
    public User login(@RequestParam() String email, @RequestParam() String password) {
        return userService.login(email, password);
    }
}