package com.example.test.service;

import com.example.test.entity.AppRole;
import com.example.test.entity.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String username, String roleName);
    AppUser loadUserByUsername(String username);
    AppUser getUserById(Long id);
    List<AppUser> listUsers();

}
