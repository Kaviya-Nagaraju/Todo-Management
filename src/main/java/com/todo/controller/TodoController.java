package com.todo.controller;

import com.todo.dto.TodoDto;
import com.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private TodoService todoService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto){
        TodoDto createdTodo=todoService.createTodo(todoDto);
        return new ResponseEntity(createdTodo, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{id}")
    ResponseEntity<TodoDto> findTodoById(@PathVariable Long id){
       TodoDto todoDto= todoService.findTodoById(id);
       return new ResponseEntity<>(todoDto,HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    ResponseEntity<List<TodoDto>> findAllTodos(){
        List<TodoDto> allTodos=todoService.findAllTodos();
        return new ResponseEntity<>(allTodos,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto,@PathVariable long id){
        TodoDto todoDtoUpdated=todoService.updateTodo(todoDto,id);
        return ResponseEntity.ok(todoDtoUpdated);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    ResponseEntity<String> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Todo deleted successfully");
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/complete")
    ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long todoId){
        TodoDto todo=todoService.completeTodo(todoId);
        return ResponseEntity.ok(todo);

    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/in-complete")
    ResponseEntity<TodoDto> incompleteTodo(@PathVariable("id") Long todoId){
        TodoDto todo=todoService.incompleteTodo(todoId);
        return ResponseEntity.ok(todo);

    }
}
