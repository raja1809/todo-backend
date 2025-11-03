package com.todo.todo_backend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.todo.todo_backend.model.Todo;

@Repository
public interface TodoRepository extends MongoRepository<Todo, String> {
    List<Todo> findByCompleted(boolean completed);
}