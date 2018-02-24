package com.rethinkwebdesign.myteamer.config;

import com.rethinkwebdesign.myteamer.model.Role;
import com.rethinkwebdesign.myteamer.model.User;
import com.rethinkwebdesign.myteamer.repository.RoleRepository;
import com.rethinkwebdesign.myteamer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        LoadUsers();
    }

    private void LoadUsers() {
        List<Role> roles = new ArrayList<>();
        Role role = roleRepository.save(new Role("ADMIN"));
        roles.add(role);
        userRepository.save(new User("dsmithco@gmail.com", "Rethink44", roles));
    }
}