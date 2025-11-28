package com.todo.microservice.service;

import com.todo.microservice.models.Status;
import com.todo.microservice.models.Todo;

import java.util.List;

public interface ITodoService {

    Todo addTodo(Todo todo);

    Todo getById(String id) throws Exception;

    Iterable<Todo> getAll();

    Todo update(String id, Todo todo);

    void delete(String id);

    List<Todo> getByPriority(String priority);

    List<Todo> getByStatus(Status status);

    List<Todo> getByPriorityAndStatus(String priority, Status status);


}
