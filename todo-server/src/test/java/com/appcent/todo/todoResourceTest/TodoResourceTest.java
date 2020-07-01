package com.appcent.todo.todoResourceTest;

import com.appcent.todo.todo.Todo;
import com.appcent.todo.todo.TodoResource;
import com.appcent.todo.todo.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoResourceTest {
    @InjectMocks
    TodoResource todoResource;

    @Mock
    TodoService todoService;

    @Test
    public void testSaveTodo()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Todo todo = new Todo();
        todo.setId("1");
        when(todoService.createTodo(any(Todo.class),any(String.class))).thenReturn(todo);

        Todo todoToBeSaved = new Todo("1", "bartu", "Learn to fly a plane", new Date(),true);
        ResponseEntity<Todo> responseEntity = todoResource.saveTodo("username",todoToBeSaved);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
    }

    @Test
    public void testFindAll(){

        List<Todo> todos = new ArrayList();
        todos.add(new Todo("1","bartu","code a test",new Date(),false));
        todos.add(new Todo("2","bartu","run the test",new Date(),false));
        todos.add(new Todo("3","bartu","see if its passed",new Date(),false));

        when(todoService.findAll("bartu")).thenReturn(todos);
        List<Todo> result = todoResource.getAllTodos("bartu");

        // then
        assertThat(result.size()).isEqualTo(3);

        assertThat(result.get(0).getUsername())
                .isEqualTo(todos.get(1).getUsername());

        assertThat(result.get(1).getUsername())
                .isEqualTo(todos.get(2).getUsername());
    }

    @Test
    public void testDeleteTodo(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        List<Todo> todos = new ArrayList();
        todos.add(new Todo("1","bartu","code a test",new Date(),false));
        todos.add(new Todo("2","bartu","run the test",new Date(),false));
        todos.add(new Todo("3","bartu","see if its passed",new Date(),false));

        when(todoService.deleteById("1")).thenReturn(todos.get(1));
        ResponseEntity<Void> responseEntity = todoResource.deleteTodo("bartu","1");

        // then
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
    }

    @Test
    public void testGetTodo(){

        Todo todo = new Todo("1","bartu","code a test",new Date(),false);

        when(todoService.findById("1")).thenReturn(todo);
        Todo result = todoResource.getTodo("bartu","1");

        // then
        assertThat(result).isEqualTo(todo);
    }

    @Test
    public void updateTodo(){

        Todo oldTodo = new Todo("1","bartu","code a test",new Date(),false);
        Todo updatedTodo = new Todo("1","bartu","test the update method",new Date(),false);
        when(todoService.update("bartu","1",updatedTodo)).thenReturn(updatedTodo);
        ResponseEntity<Todo> result = todoResource.updateTodo("bartu","1",updatedTodo);

        // then
        assertThat(result.getBody()).isEqualTo(updatedTodo);
    }

}
