package com.example.donaresange.controller;

import com.example.donaresange.model.User;
import com.example.donaresange.service.UserService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user/register")
    public Boolean register(@RequestParam() String email, @RequestParam() String password, @RequestParam() String confirmPassword, @RequestParam() String name, @RequestParam() String bloodType, @RequestParam() String address) {
        return userService.register(email, password, confirmPassword, name, bloodType, address);
    }

    @GetMapping("/user/login")
    public User login(@RequestParam() String email, @RequestParam() String password) {
        return userService.login(email, password);
    }
}

/*

   {
       "email" : "",
       "password": ""

   }

 */