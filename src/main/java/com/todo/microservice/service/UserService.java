package com.todo.microservice.service;

import com.todo.microservice.exceptions.BadRequestException;
import com.todo.microservice.models.Address;
import com.todo.microservice.models.User;
import com.todo.microservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User addUser(User user) throws Exception {
        if (user.getEmail().isEmpty()) {
            throw new Exception("Email already exists");
        }

        if (user.getUsername().isEmpty()) {
            throw new Exception("User name already exists");
        }

        if (user.getPassword().isEmpty()) {
            throw new Exception("Password already exists");
        }

        User existingUserByUserName = userRepository.findByUsername(user.getUsername());
        if(existingUserByUserName != null) {
            throw new Exception("User with Given user name exists");
        }

        boolean emailExist = userRepository.existsByEmail(user.getEmail());
        if (emailExist) {
            throw new Exception("Email already already registered to another user  ");
        }

        if (user.getPhoneNumber()== null || user.getPhoneNumber().length() != 11 || user.getPhoneNumber().charAt(0) != '0') {
            throw new Exception("Phone number is invalid");
        }

        return userRepository.save(user);
    }

}
