//package com.appcent.todo.todo;
//
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class todoHardcodedService {
//    private static List<Todo> todos = new ArrayList();
//    private static long idCounter  = 0;
//
//    static {
//        todos.add(new Todo(++idCounter,"bartu","Learn to code",new Date(),false));
//        todos.add(new Todo(++idCounter,"bartu","Learn to laugh",new Date(),false));
//        todos.add(new Todo(++idCounter,"bartu","Learn to love",new Date(),false));
//    }
//    public List<Todo> findAll(){
//        return todos;
//    }
//
//    public Todo save(Todo todo){
//        if(todo.getId()==null || todo.getId()==-1 ){
//            todo.setId(++idCounter);
//            todos.add(todo);
//        }else{
//            deleteById(todo.getId());
//            todos.add(todo);
//        }
//        return todo;
//    }
//
//    public Todo deleteById(long id){
//        Todo todo = findById(id);
//        if(todo == null) return null;
//
//        if(todos.remove(todo)){
//            return todo;
//        }
//        return null;
//    }
//
//    public Todo findById(long id) {
//        for(Todo todo: todos){
//            if(todo.getId() == id){
//                return todo;
//            }
//        }
//        return null;
//    }
//
//
//}
