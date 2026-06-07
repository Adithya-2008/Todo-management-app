package com.example.todo_management.service.Impl;

import com.example.todo_management.Dao.RoleDao;
import com.example.todo_management.dto.LoginDto;
import com.example.todo_management.dto.RegisterDto;
import com.example.todo_management.Dao.UserDao;
import com.example.todo_management.entity.Role;
import com.example.todo_management.entity.User;
import com.example.todo_management.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final RoleDao roleDao;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(
            UserDao userDao,
            PasswordEncoder passwordEncoder,
            RoleDao roleDao, AuthenticationManager authenticationManager) {

        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.roleDao = roleDao;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String register(RegisterDto registerDto) {

        if (userDao.existByuserName(registerDto.getUserName())) {
            throw new RuntimeException(
                    "Username already exists");
        }

        if (userDao.existByEmail(registerDto.getEmail())) {
            throw new RuntimeException(
                    "Email already exists");
        }

        User user = new User();

        user.setName(registerDto.getName());
        user.setUserName(registerDto.getUserName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(
                passwordEncoder.encode(
                        registerDto.getPassword()
                )
        );

        Role userRole =
                roleDao.findByName("ROLE_USER");

        if (userRole == null) {
            throw new RuntimeException(
                    "ROLE_USER not found");
        }

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        user.setRoles(roles);

        userDao.createUser(user);

        return "User Registered Successfully";
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUserNameOrEmail(),
                loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User logged in successfully";
    }
}


