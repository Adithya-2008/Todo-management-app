package com.example.todo_management.service.Impl;

import com.example.todo_management.Dao.TodoDao;
import com.example.todo_management.dto.TodoDto;
import com.example.todo_management.entity.Todo;
import com.example.todo_management.mapper.TodoMapper;
import com.example.todo_management.service.TodoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoDao todoDao;

    public TodoServiceImpl(TodoDao todoDao) {
        this.todoDao = todoDao;
    }
    public TodoDto createTodo(TodoDto todoDto){
        Todo todo= TodoMapper.mapToTodo(todoDto);
        Todo savedTodo=todoDao.createTodo(todo);
        return TodoMapper.mapToTodoDto(savedTodo);
    }

    @Override
    public TodoDto getTodoById(Long id) {
        Todo todo=todoDao.getTodoById(id);
        return TodoMapper.mapToTodoDto(todo);
    }

    @Override
    public List<TodoDto> getAllTodo() {
        List<Todo> todos=todoDao.getAllTodo();
        List<TodoDto> todoDtos=todos.stream().map(todo -> TodoMapper.mapToTodoDto(todo)).toList();
        return todoDtos;
    }


    @Override
    public TodoDto updateTodoById(Long id,TodoDto todoDto) {
        Todo todo=todoDao.getTodoById(id);
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        Todo updatedTodo=todoDao.updateTodo(todo);
        return TodoMapper.mapToTodoDto(updatedTodo);
    }

    @Override
    public void deleteTodoById(Long id) {
        Todo todo=todoDao.getTodoById(id);
        todoDao.deleteTodoById(todo.getId());
    }

}
