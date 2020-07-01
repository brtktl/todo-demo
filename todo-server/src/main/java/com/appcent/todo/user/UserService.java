package com.appcent.todo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
    @Autowired
    private UserEntityRepository userRepository;

    public UserEntity createUser(UserEntity userDetails){
        UserEntity newUser = new UserEntity(userDetails.getUsername(),encoder.encode(userDetails.getPassword()));
        return userRepository.save(newUser);
    }

    public UserEntity findByUsername(String Username){
        return userRepository.findByUsername(Username);
    }
}
