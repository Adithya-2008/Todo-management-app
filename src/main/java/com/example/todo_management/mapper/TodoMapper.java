package com.example.todo_management.mapper;

import com.example.todo_management.dto.TodoDto;
import com.example.todo_management.entity.Todo;

public class TodoMapper {

    public static Todo mapToTodo(TodoDto todoDto) {

        Todo todo = new Todo();

        todo.setId(todoDto.getId());
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        return todo;
    }

    public static TodoDto mapToTodoDto(Todo todo) {

        return new TodoDto(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.isCompleted()
        );
    }
}
