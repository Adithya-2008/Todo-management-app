package com.example.todo_management.Dao;

import com.example.todo_management.entity.User;
import com.example.todo_management.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDao {
    private final UserRepository userRepository;

    public UserDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findByUserName(username);
    }

    public Boolean existByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public Optional<User> findByUserNameOrEmail(String username,String email){
        return userRepository.findByUserNameOrEmail(username,email);
    }
    public Boolean existByuserName(String username){
        return userRepository.existsByuserName(username);
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

}
