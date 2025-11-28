package com.todo.microservice.controller;


import com.todo.microservice.models.Status;
import com.todo.microservice.models.Todo;
import com.todo.microservice.service.TodoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    TodoServiceImp todoServiceImp;

    @PostMapping("/add")
    public Todo addTodo(@RequestBody Todo todo) {
        System.out.println(todo);
        return todoServiceImp.addTodo(todo);
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable String id) throws Exception {
        return todoServiceImp.getById(id);
    }
    //Get toto by given id. Throw exception if not found

    @GetMapping
    public Iterable<Todo> getAll() {
        return todoServiceImp.getAll();
    }

    //Update todo, Throw exception if not found.

    @PutMapping("/{id}")
    public Todo update(@PathVariable String id, @RequestBody Todo todo) {
        return todoServiceImp.update(id, todo);
    }

    //delete todo, Throw exception if not found
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        todoServiceImp.delete(id);
    }

    // get a list of todos by given priority
    @GetMapping("/priority/{priority}")
    public List<Todo> getByPriority(@PathVariable String priority) {
        return todoServiceImp.getByPriority(priority);
    }

    // get a list of todos by a given status
    @GetMapping("/status/{status}")
    public List<Todo> getByStatus(@PathVariable Status status) {
        return todoServiceImp.getByStatus(status);
    }

    // get a list of todos by given priority and status
    @GetMapping("/filter")
    public List<Todo> getByPriorityAndStatus(@RequestParam String priority, @RequestParam Status status) {
        return todoServiceImp.getByPriorityAndStatus(priority, status);
    }
    //Implemented filtering (which could take different types of parameters at the same time - e,g status, date, priority)
}
