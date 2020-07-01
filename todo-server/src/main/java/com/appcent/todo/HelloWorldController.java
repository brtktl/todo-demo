package com.appcent.todo;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    @GetMapping(path="/hello-world")
    @CrossOrigin(origins="http://localhost:4200")
    public String helloWorld()
    {
        return "Hello world";
    }
}
