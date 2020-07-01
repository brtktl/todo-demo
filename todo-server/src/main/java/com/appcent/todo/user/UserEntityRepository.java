package com.appcent.todo.user;


import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserEntityRepository extends PagingAndSortingRepository<UserEntity, String> {
    UserEntity findByUsername(String username);
}
