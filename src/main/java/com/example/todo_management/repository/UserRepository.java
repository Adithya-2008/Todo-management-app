package com.example.todo_management.repository;

import com.example.todo_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);

    Boolean existsByEmail(String email);

    Optional<User> findByUserNameOrEmail(String userName,String email);

    Boolean existsByuserName(String username);


}