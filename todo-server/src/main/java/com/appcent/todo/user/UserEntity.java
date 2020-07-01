package com.appcent.todo.user;

import com.sun.istack.NotNull;

import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.annotation.Id;
import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;


@Document
public class UserEntity {

    @Id @GeneratedValue(strategy = UNIQUE)
    private String id;

    @NotNull
    private String username;
    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }
    public UserEntity() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
