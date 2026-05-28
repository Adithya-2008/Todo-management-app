package com.example.todo_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDto {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
}
