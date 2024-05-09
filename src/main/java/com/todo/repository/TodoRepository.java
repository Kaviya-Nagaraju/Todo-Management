package com.todo.repository;

import com.todo.entity.Todos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todos,Long> {
}
