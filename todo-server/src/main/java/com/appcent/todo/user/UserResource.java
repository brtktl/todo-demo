package com.appcent.todo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class UserResource {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserEntity newUser) {
        //return userService.createUser(newUser);
        UserEntity foundUser = userService.findByUsername(newUser.getUsername());
        // or userService.findUserByName(username);
        if(foundUser==null){
            return getSuccessfulResponse(userService.createUser(newUser));
        }else{
            return getBadRequestResponse(String.format("User with name : %s already exists", newUser.getUsername()));
        }
    }

//    @GetMapping("/register-user")
//    public List<UserEntity> getUserByName() {
//        return (List<UserEntity>) userRepository.findAll();
//    }

    protected ResponseEntity getSuccessfulResponse(Object result) {
        return new ResponseEntity(result, HttpStatus.OK);
    }

    protected ResponseEntity getBadRequestResponse(String message) {
        return new ResponseEntity((message),HttpStatus.BAD_REQUEST);
    }
}
