package com.todo.microservice.todo.repository;


import com.todo.microservice.todo.model.Status;
import com.todo.microservice.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, String> {

    Todo getTodoById(String id);

    List<Todo> findByPriority(String priority);

    List<Todo> findByStatus(Status status);

    List<Todo> findByPriorityAndStatus(String priority, Status status);
}
