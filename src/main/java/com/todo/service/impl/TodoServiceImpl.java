package com.todo.service.impl;

import com.todo.dto.TodoDto;
import com.todo.entity.Todos;
import com.todo.exceptions.ResourceNotFoundException;
import com.todo.repository.TodoRepository;
import com.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    private ModelMapper modelMapper;
    @Override
    public TodoDto createTodo(TodoDto todoDto) {
        Todos todos=modelMapper.map(todoDto,Todos.class);
        Todos savedTodos=todoRepository.save(todos);
        TodoDto savedTodoDto=modelMapper.map(savedTodos,TodoDto.class);
        return savedTodoDto;
    }

    @Override
    public TodoDto findTodoById(Long id) {
        Todos todo=todoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Todo not found with id: "+id));
        TodoDto todoDto=modelMapper.map(todo,TodoDto.class);
        return todoDto;
    }

    @Override
    public List<TodoDto> findAllTodos() {
        List<Todos> todos=todoRepository.findAll();
        return todos.stream().map(todo->modelMapper
                .map(todo,TodoDto.class)).collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
        Todos Todo=todoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Todo not found with id "+id));
        Todo.setTitle(todoDto.getTitle());
        Todo.setDescription(todoDto.getDescription());
        Todo.setCompleted(todoDto.isCompleted());
        Todos updatedTodo=todoRepository.save(Todo);
        return modelMapper.map(updatedTodo,TodoDto.class);

    }

    @Override
    public Void deleteTodo(Long id) {
        Todos todo=todoRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Todo not found with id: "+id));
        todoRepository.deleteById(id);

        return null;
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todos todo=todoRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Todo not found with id: "+id));
        todo.setCompleted(Boolean.TRUE);
        Todos updatedTodo=todoRepository.save(todo);
        return modelMapper.map(updatedTodo,TodoDto.class);
    }

    @Override
    public TodoDto incompleteTodo(Long id) {
        Todos todo=todoRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Todo not found with id: "+id));
        todo.setCompleted(Boolean.FALSE);
        Todos updatedTodo=todoRepository.save(todo);
        return modelMapper.map(updatedTodo,TodoDto.class);
    }
}
