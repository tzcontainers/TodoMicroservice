package com.todo.microservice.repository;


import com.todo.microservice.models.Status;
import com.todo.microservice.models.Todo;
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
