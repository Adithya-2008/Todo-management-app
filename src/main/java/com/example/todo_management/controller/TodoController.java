package com.example.todo_management.controller;

import com.example.todo_management.dto.TodoDto;
import com.example.todo_management.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/createTodo")
    public ResponseEntity<TodoDto>createTodo(@RequestBody TodoDto todoDto){
        TodoDto savedTodo=todoService.createTodo(todoDto);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @GetMapping("/getTodoById/{id}")
    public ResponseEntity<TodoDto>getTodoById(@PathVariable Long id){
        TodoDto todoDto=todoService.getTodoById(id);
        return ResponseEntity.ok(todoDto);
    }

    @GetMapping("/getAllTodo")
    public ResponseEntity<List<TodoDto>>getAllTodo(){
        List<TodoDto> todoDtos=todoService.getAllTodo();
        return ResponseEntity.ok(todoDtos);
    }

    @PutMapping("/updateTodoById/{id}")
    public ResponseEntity<TodoDto>updateTodoById(@PathVariable Long id,@RequestBody TodoDto todoDto){
        TodoDto updateTodo=todoService.updateTodoById(id,todoDto);
        return ResponseEntity.ok(updateTodo);
    }

    @DeleteMapping("/deleteTodoById/{id}")
    public ResponseEntity<String>deleteTodoById(@PathVariable Long id){
        todoService.deleteTodoById(id);
        return ResponseEntity.ok("Deleted todo with ID:"+id);
    }

    @PutMapping("completeTodo/{id}")
    public ResponseEntity<String>completeTodo(@PathVariable Long id){
        todoService.completeTodo(id);
        return ResponseEntity.ok("Complete todo with ID:"+id);
    }

    @PutMapping("/incompleteTodo/{id}")
    public ResponseEntity<String> incompleteTodo(@PathVariable Long id) {
        todoService.incompleteTodo(id);
        return ResponseEntity.ok("Incomplete todo with ID:"+id);
    }
}
