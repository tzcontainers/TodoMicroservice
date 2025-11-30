package com.todo.microservice.user.controller;

import com.todo.microservice.user.model.User;
import com.todo.microservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signUp")
    public User signUp(@RequestBody User user) throws Exception {
        return userService.addUser(user);
    }
}