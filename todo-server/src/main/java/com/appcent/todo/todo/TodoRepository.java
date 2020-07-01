package com.appcent.todo.todo;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TodoRepository extends PagingAndSortingRepository<Todo, String> {


    List<Todo> findTodosByUsername(String username);
}
