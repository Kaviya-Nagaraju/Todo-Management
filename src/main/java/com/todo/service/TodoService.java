package com.todo.service;

import com.todo.dto.TodoDto;
import com.todo.entity.Todos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodoService {
    TodoDto createTodo(TodoDto todoDto);
    TodoDto findTodoById(Long id);

    List<TodoDto> findAllTodos();
    TodoDto updateTodo(TodoDto todoDto,Long id);

    Void deleteTodo(Long id);
    TodoDto completeTodo(Long id);

    TodoDto incompleteTodo(Long id);
}
