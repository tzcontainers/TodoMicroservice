package com.todo.microservice.controller;


import com.todo.microservice.models.Todo;
import com.todo.microservice.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    TodoService todoService;

    @PostMapping("/add")
    public Todo addTodo(@RequestBody Todo todo) {
        System.out.println(todo);
       return todoService.addTodo(todo);
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable String id) throws Exception {
        return todoService.getById(id);
    }

    //Get toto by given id. Throw exception if not found

    //Update todo, Throw exception if not found.

    //delete todo, Throw exception if not found

    // get a list of todos by given priority

    // get a list of todos by a given status

    // get a list of todos by given priority and status

    //Implemented filtering (which could take different types of parameters at the same time - e,g status, date, priority)
}
