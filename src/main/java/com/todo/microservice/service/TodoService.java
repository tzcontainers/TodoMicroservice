package com.todo.microservice.service;

import com.todo.microservice.models.Todo;
import com.todo.microservice.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    TodoRepository todoRepository;


    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
        //return todo;
    }

    public Todo getById(String id) throws Exception {

        Todo todo = todoRepository.getTodoById(id);
        if(todo == null) {

            throw new Exception(String.format("Todo with id %s is not found", id));
        }
        return todo;
    }
}
