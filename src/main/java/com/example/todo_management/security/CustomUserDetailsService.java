package com.example.todo_management.security;

import com.example.todo_management.entity.User;
import com.example.todo_management.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService{
    private UserRepository userRepository;
    private com.example.todo_management.Dao.UserDao userDao;

    @Autowired
    public CustomUserDetailsService(com.example.todo_management.Dao.UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user= userDao.findByUserNameOrEmail(usernameOrEmail,usernameOrEmail).orElseThrow(()->new RuntimeException("USER NOT FOUND"));
        Set<GrantedAuthority> authorities=user.getRoles().stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(
                usernameOrEmail,
                user.getPassword(),
                authorities);
    }
}
