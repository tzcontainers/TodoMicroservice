package com.todo.microservice.todo.controller;


import com.todo.microservice.todo.model.Status;
import com.todo.microservice.todo.model.Todo;
import com.todo.microservice.todo.service.TodoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    @Autowired
    private TodoServiceImp todoServiceImp;

    // Create a new todo
    @PostMapping("/add")
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {
        Todo createdTodo = todoServiceImp.addTodo(todo);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    // Get todo by ID
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable String id) throws Exception {
        Todo todo = todoServiceImp.getById(id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    // Get all todos
    @GetMapping
    public ResponseEntity<Iterable<Todo>> getAll() {
        Iterable<Todo> todos = todoServiceImp.getAll();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    // Update todo
    @PutMapping("/{id}")
    public ResponseEntity<Todo> update(@PathVariable String id, @RequestBody Todo todo) {
        Todo updatedTodo = todoServiceImp.update(id, todo);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    // Delete todo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        todoServiceImp.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get todos by priority
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Todo>> getByPriority(@PathVariable String priority) {
        List<Todo> todos = todoServiceImp.getByPriority(priority);
        return new ResponseEntity<>(todos, todos.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    // Get todos by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Todo>> getByStatus(@PathVariable Status status) {
        List<Todo> todos = todoServiceImp.getByStatus(status);
        return new ResponseEntity<>(todos, todos.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    // Filter todos by multiple parameters
    @GetMapping("/filter")
    public ResponseEntity<List<Todo>> getByPriorityAndStatus(@RequestParam String priority, @RequestParam Status status) {
        List<Todo> todos = todoServiceImp.getByPriorityAndStatus(priority, status);
        return new ResponseEntity<>(todos, todos.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
}


//
//    // Create a new todo
//    @PostMapping("/add")
//    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {
//        try {
//            Todo createdTodo = todoServiceImp.addTodo(todo);
//            return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    // Get todo by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Todo> getTodoById(@PathVariable String id) {
//        try {
//            Todo todo = todoServiceImp.getById(id);
//            return new ResponseEntity<>(todo, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }
//
//    // Get all todos
//    @GetMapping
//    public ResponseEntity<Iterable<Todo>> getAll() {
//        try {
//            Iterable<Todo> todos = todoServiceImp.getAll();
//            return new ResponseEntity<>(todos, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    // Update todo
//    @PutMapping("/{id}")
//    public ResponseEntity<Todo> update(@PathVariable String id, @RequestBody Todo todo) {
//        try {
//            Todo updatedTodo = todoServiceImp.update(id, todo);
//            return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }
//
//    // Delete todo
//    @DeleteMapping("/{id}")
//    public ResponseEntity<HttpStatus> delete(@PathVariable String id) {
//        try {
//            todoServiceImp.delete(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    // Get todos by priority
//    @GetMapping("/priority/{priority}")
//    public ResponseEntity<List<Todo>> getByPriority(@PathVariable String priority) {
//        try {
//            List<Todo> todos = todoServiceImp.getByPriority(priority);
//            if (todos.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(todos, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    // Get todos by status
//    @GetMapping("/status/{status}")
//    public ResponseEntity<List<Todo>> getByStatus(@PathVariable Status status) {
//        try {
//            List<Todo> todos = todoServiceImp.getByStatus(status);
//            if (todos.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(todos, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    // Get todos by priority and status
//    @GetMapping("/filter")
//    public ResponseEntity<List<Todo>> getByPriorityAndStatus(@RequestParam String priority, @RequestParam Status status) {
//        try {
//            List<Todo> todos = todoServiceImp.getByPriorityAndStatus(priority, status);
//            if (todos.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(todos, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
