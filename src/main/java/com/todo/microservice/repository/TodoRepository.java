package com.todo.microservice.repository;


import com.todo.microservice.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<Todo, String> {

    Todo getTodoById(String id);
}
