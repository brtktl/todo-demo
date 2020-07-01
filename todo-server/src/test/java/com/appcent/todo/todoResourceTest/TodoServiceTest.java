package com.appcent.todo.todoResourceTest;

import com.appcent.todo.todo.Todo;
import com.appcent.todo.todo.TodoRepository;
import com.appcent.todo.todo.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @InjectMocks
    TodoService todoService;

    @Mock
    TodoRepository todoRepository;

    @Test
    public void testFindAll(){

        List<Todo> todos = new ArrayList();
        todos.add(new Todo("1","bartu","code a test",new Date(),false));
        todos.add(new Todo("2","bartu","run the test",new Date(),false));
        todos.add(new Todo("3","bartu","see if its passed",new Date(),false));

        when(todoRepository.findTodosByUsername("bartu")).thenReturn(todos);
        List<Todo> result = todoService.findAll("bartu");

        // then
        assertThat(result.size()).isEqualTo(3);

        assertThat(result.get(0).getUsername())
                .isEqualTo(todos.get(1).getUsername());

        assertThat(result.get(1).getUsername())
                .isEqualTo(todos.get(2).getUsername());

        assertThat(result.get(1).getUsername())
                .isNotEqualTo("Orhan");
    }

    @Test
    public void testCreateTodo(){

        Todo todoToBeSaved = new Todo("2","","run the test",new Date(),false);
        Todo todoToBeReturned = new Todo("2","bartu","run the test",new Date(),false);

        when(todoRepository.save(any(Todo.class))).thenReturn(todoToBeReturned);
        Todo result = todoService.createTodo(todoToBeSaved,"bartu");

        // then
        assertThat(result.getUsername())
                .isEqualTo("bartu");

        assertThat(result.getDescription())
                .isEqualTo("run the test");

        assertThat(result.isDone())
                .isEqualTo(false);
    }

    @Test
    public void testDeleteTodo(){

        Todo todoToBeDeleted = new Todo("2","bartu","run the delete",new Date(),false);

        when(todoRepository.findById("2")).thenReturn(java.util.Optional.of(todoToBeDeleted));
        Todo result = todoService.deleteById("2");

        // then
        assertThat(result)
                .isEqualTo(todoToBeDeleted);

    }
    @Test
    public void testFindById(){

        Todo todoToFind = new Todo("2","bartu","run the test",new Date(),false);

        when(todoRepository.findById("2")).thenReturn(java.util.Optional.of(todoToFind));
        Todo result = todoService.deleteById("2");

        // then
        assertThat(result)
                .isEqualTo(todoToFind);

    }
}
