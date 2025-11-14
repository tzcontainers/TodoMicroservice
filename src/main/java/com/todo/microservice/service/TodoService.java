package com.todo.microservice.service;

import com.todo.microservice.models.Status;
import com.todo.microservice.models.Todo;
import com.todo.microservice.repository.TodoRepository;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
    public Iterable<Todo> getAll() {
        return  todoRepository.findAll();
    }

    public Todo update(String id, Todo todo) {
        Todo existing = todoRepository.getTodoById(id);

//        if(existing == null) {
//            return null;
//        }
        existing.setUserId(todo.getUserId());
        existing.setTitle(todo.getTitle());
        existing.setDescription(todo.getDescription());
        existing.setStatus(todo.getStatus());
        existing.setPriority(todo.getPriority());
        existing.setDueDate(todo.getDueDate());

        return todoRepository.save(existing);
    }
    public void delete(String id) {
        todoRepository.deleteById(id);
    }
    public List<Todo> getByPriority(String priority) {
        return todoRepository.findByPriority(priority);
    }

    public List<Todo> getByStatus(Status status) {
        return todoRepository.findByStatus(status);
    }

    public List<Todo> getByPriorityAndStatus(String priority, Status status) {
        return todoRepository.findByPriorityAndStatus(priority, status);
    }
}
