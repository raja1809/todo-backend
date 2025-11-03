package com.todo.todo_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todo_backend.model.Todo;
import com.todo.todo_backend.service.TodoService;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "*")
public class TodoController {
    
    @Autowired
    private TodoService todoService;
    
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable String id) {
        return todoService.getTodoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(todoService.createTodo(todo));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String id, @RequestBody Todo todo) {
        try {
            return ResponseEntity.ok(todoService.updateTodo(id, todo));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/completed")
    public ResponseEntity<List<Todo>> getCompletedTodos() {
        return ResponseEntity.ok(todoService.getCompletedTodos());
    }
    
    @GetMapping("/incomplete")
    public ResponseEntity<List<Todo>> getIncompleteTodos() {
        return ResponseEntity.ok(todoService.getIncompleteTodos());
    }
}