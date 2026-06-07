package com.example.todo_management.Dao;

import com.example.todo_management.entity.Role;
import com.example.todo_management.repository.RoleRepository;
import org.springframework.stereotype.Component;

@Component
public class RoleDao {
    private final RoleRepository roleRepository;

    public RoleDao(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(String name){
        Role userRole= roleRepository.findByName(name);
        return userRole;
    }
}
