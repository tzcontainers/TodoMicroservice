package com.todo.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.todo.microservice.repository")
@EntityScan(basePackages = "com.todo.microservice.models")
public class TodoApp {

    public static void main(String[] args) {
        SpringApplication.run(TodoApp.class, args);
    }
}
