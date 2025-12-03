package com.todo.microservice.user.controller;

import com.todo.microservice.user.model.User;
import com.todo.microservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<User> signUp(@RequestBody User user) throws Exception {
        User addUser = userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}