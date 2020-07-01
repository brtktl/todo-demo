package com.appcent.todo.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class TodoResource {
    @Autowired
    private TodoService todoService;

    @GetMapping("/users/{username}/todos")
    public List<Todo> getAllTodos(@PathVariable String username){
        return todoService.findAll(username);
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username,@PathVariable String id){
        Todo todo = todoService.deleteById(id);
        if(todo!=null){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo getTodo(@PathVariable String username,@PathVariable String id){
        return todoService.findById(id);
    }

    @PutMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String username,@PathVariable String id,@RequestBody Todo todo){
         Todo updated  = todoService.update(username,id,todo);
         return new ResponseEntity<Todo>(todo,HttpStatus.OK);
    }
    @PostMapping("/users/{username}/todos")
    public ResponseEntity<Todo> saveTodo(@PathVariable String username,@RequestBody Todo todo){
        Todo createdTodo  = todoService.createTodo(todo,username);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/do-todo/{id}")
    public ResponseEntity<Todo> doTodo(@PathVariable String id){
        Todo todo  = todoService.doTodo(id);
        return new ResponseEntity<Todo>(todo,HttpStatus.OK);
    }

    @GetMapping("/undo-todo/{id}")
    public ResponseEntity<Todo> undoTodo(@PathVariable String id){
        Todo todo  = todoService.undoTodo(id);
        return new ResponseEntity<Todo>(todo,HttpStatus.OK);
    }




}
