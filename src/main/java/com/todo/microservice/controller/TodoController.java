package com.todo.microservice.controller;


import com.todo.microservice.models.Status;
import com.todo.microservice.models.Todo;
import com.todo.microservice.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public Iterable<Todo> getAll() {
        return todoService.getAll();
    }

    //Update todo, Throw exception if not found.

    @PutMapping("/{id}")
    public Todo update(@PathVariable String id, @RequestBody Todo todo) {
        return todoService.update(id, todo);
    }

    //delete todo, Throw exception if not found
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        todoService.delete(id);
    }

    // get a list of todos by given priority
    @GetMapping("/priority/{priority}")
    public List<Todo> getByPriority(@PathVariable String priority) {
        return todoService.getByPriority(priority);
    }

    // get a list of todos by a given status
    @GetMapping("/status/{status}")
    public List<Todo> getByStatus(@PathVariable Status status) {
        return todoService.getByStatus(status);
    }

    // get a list of todos by given priority and status
    @GetMapping("/filter")
    public List<Todo> getByPriorityAndStatus(@RequestParam String priority,@RequestParam Status status) {
        return todoService.getByPriorityAndStatus(priority, status);
    }

    //Implemented filtering (which could take different types of parameters at the same time - e,g status, date, priority)
}
