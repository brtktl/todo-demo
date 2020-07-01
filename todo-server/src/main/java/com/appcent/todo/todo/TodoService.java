package com.appcent.todo.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    protected TodoRepository todoRepository;

    public List<Todo> findAll(String username){
        return (List<Todo>) todoRepository.findTodosByUsername(username);
    }

    public Todo createTodo(Todo todo,String username){
        todo.setUsername(username);
        return todoRepository.save(todo);
    }
    public Todo update(String username,String id,Todo todoDetails){
        Todo todo = findById(id);
        if(todo!=null){
            todoDetails.setDone(todo.isDone());
            this.deleteById(id);
            return this.createTodo(todoDetails,username);
        }
        return null;
    }

    public Todo deleteById(String id){
        Todo todo = findById(id);
        if(todo == null) return null;
        todoRepository.deleteById(id);
        return todo;
    }

    public Todo findById(String id) {
        Optional<Todo> todo = todoRepository.findById(id);
        return todo.orElse(null);
    }

    public Todo doTodo(String id){
        Todo todo = findById(id);
        todo.setDone(true);
        return todoRepository.save(todo);
    }

    public Todo undoTodo(String id){
        Todo todo = findById(id);
        todo.setDone(false);
        return todoRepository.save(todo);
    }


}
