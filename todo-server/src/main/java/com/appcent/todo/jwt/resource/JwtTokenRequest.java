package com.appcent.todo.jwt.resource;

import java.io.Serializable;

public class  JwtTokenRequest implements Serializable {

  private static final long serialVersionUID = -5616176897013108345L;

  private String username;
    private String password;

    public JwtTokenRequest() {
        super();
    }

//    {
//        "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpbjI4bWludXRlcyIsImV4cCI6MTU5Mzg3MzY1NywiaWF0IjoxNTkzMjY4ODU3fQ.A6qc3IKfWR4rtks6UJQwSP33XLk7jdAj-lO3pNzHUXL-Dhn4yZPfutD0fBfPUFxq9zSaFelsXeuSzesOQZS88g"
//    }

    public JwtTokenRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
