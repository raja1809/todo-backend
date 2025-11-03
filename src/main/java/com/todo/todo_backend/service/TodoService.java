package com.todo.todo_backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.todo_backend.model.Todo;
import com.todo.todo_backend.repository.TodoRepository;

@Service
public class TodoService {
    
    @Autowired
    private TodoRepository todoRepository;
    
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }
    
    public Optional<Todo> getTodoById(String id) {
        return todoRepository.findById(id);
    }
    
    public Todo createTodo(Todo todo) {
        todo.setCreatedAt(LocalDateTime.now());
        todo.setUpdatedAt(LocalDateTime.now());
        todo.setCompleted(false);
        return todoRepository.save(todo);
    }
    
    public Todo updateTodo(String id, Todo todoDetails) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        
        todo.setTitle(todoDetails.getTitle());
        todo.setDescription(todoDetails.getDescription());
        todo.setCompleted(todoDetails.isCompleted());
        todo.setUpdatedAt(LocalDateTime.now());
        
        return todoRepository.save(todo);
    }
    
    public void deleteTodo(String id) {
        todoRepository.deleteById(id);
    }
    
    public List<Todo> getCompletedTodos() {
        return todoRepository.findByCompleted(true);
    }
    
    public List<Todo> getIncompleteTodos() {
        return todoRepository.findByCompleted(false);
    }
}