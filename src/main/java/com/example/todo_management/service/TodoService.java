package com.example.todo_management.service;

import com.example.todo_management.dto.TodoDto;
import org.springframework.stereotype.Component;

import java.util.List;


public interface TodoService {
    public TodoDto createTodo(TodoDto todoDto);

    public TodoDto getTodoById(Long id);

    public List<TodoDto>getAllTodo();

    public TodoDto updateTodoById(Long id,TodoDto todoDto);

    public void deleteTodoById(Long id);

    public void completeTodo(Long id);

    void incompleteTodo(Long id);
}
