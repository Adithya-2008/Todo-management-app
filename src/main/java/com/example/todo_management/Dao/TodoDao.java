package com.example.todo_management.Dao;

import com.example.todo_management.entity.Todo;
import com.example.todo_management.repository.TodoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TodoDao {
    private final TodoRepository todoRepository;

    public TodoDao(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todo){
        return todoRepository.save(todo);
    }

    public Todo getTodoById(Long id){
        return todoRepository.findById(id).orElseThrow(()->new RuntimeException("Todo with id " + id + " not found"));
    }

    public List<Todo> getAllTodo(){
        List<Todo> todos=todoRepository.findAll();
        return todos;
    }

    public Todo updateTodo(Todo todo){
        return todoRepository.save(todo);
    }

    public void deleteTodoById(Long id){
        todoRepository.deleteById(id);
    }
}
