package com.example.app.service;

import com.example.app.entity.AppRole;
import com.example.app.entity.AppUser;

import java.util.List;


public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    AppUser updateUser(Long id, AppUser appUser);
    void addRoleToUser(String username, String roleName);
    AppUser loadUserByUsername(String username);
    AppUser getUserById(Long id);
    AppRole getRoleByroleName(String roleName);
    AppRole getRoleById(Long id);
    void deleteUser(Long id);
    List<AppUser> listUsers();


}
