package com.todo.todo_backend.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "todos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    
    @Id
    private String id;
    
    private String title;
    
    private String description;
    
    private boolean completed;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}