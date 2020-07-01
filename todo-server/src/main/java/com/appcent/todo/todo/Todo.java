package com.appcent.todo.todo;

import org.springframework.data.couchbase.core.mapping.Document;

import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.annotation.Id;
import java.util.Date;
import java.util.Objects;

import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

@Document
public class Todo {

    @Id
    @GeneratedValue(strategy = UNIQUE)
    private String id;

    private String username;
    private String description;
    private Date targetDate;
    private boolean isDone;

    public Todo(){

    }
    public Todo(String id, String username, String description, Date targetDate, boolean isDone) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.isDone = isDone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return getId().equals(todo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
