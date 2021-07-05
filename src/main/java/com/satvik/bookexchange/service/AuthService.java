package com.satvik.bookexchange.service;

import com.satvik.bookexchange.entity.Role;
import com.satvik.bookexchange.entity.User;
import com.satvik.bookexchange.repository.RoleRepository;
import com.satvik.bookexchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User register(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        assignRole(user, "USER");
        return userRepository.save(user);
    }

    public void setRole(String email, String roleName) {
        User user = userRepository.findByEmail(email).get();
        System.out.println("User fetched: "+user.getName()+" "+user.getId());
        assignRole(user, roleName);
        userRepository.save(user);
    }

    private void assignRole(User user, String roleName){
        Role role = roleRepository.findByRoleName(roleName);
        System.out.println("Role fetched: "+role.getId()+" "+role.getRoleName());
        Set<Role> user_roles = user.getRoles();
        if(user_roles == null) {
            System.out.println("welppp! No roles yet for "+user.getEmail());
            user_roles = new HashSet<>();
        }
        user_roles.add(role);
        user.setRoles(user_roles);
    }
}
