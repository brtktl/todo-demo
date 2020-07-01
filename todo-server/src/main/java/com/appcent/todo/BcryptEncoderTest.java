package com.appcent.todo;

import com.appcent.todo.user.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BcryptEncoderTest {

    @Autowired
    private static UserEntityRepository userEntityRepository;


//    public static void main(String[] args) {
//        BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
////        for (int i=0;i<=10;i++){
////            String encoded = encoder.encode("password@!23@E+");
////            System.out.println(encoded);
////        }
//        UserEntity user = new UserEntity();
//        user.setId("someId");
//        user.setPassword("password");
//        user.setUsername("bilbo");
//
//        userEntityRepository.save(user);
//        //read after write
//        Optional<UserEntity> savedUser = userEntityRepository.findById(user.getId());
//        System.out.println(savedUser.get());
//    }
}
