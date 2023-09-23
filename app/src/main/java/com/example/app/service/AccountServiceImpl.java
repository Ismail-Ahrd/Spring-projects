package com.example.app.service;

import com.example.app.entity.AppRole;
import com.example.app.entity.AppUser;
import com.example.app.repository.AppRoleRepository;
import com.example.app.repository.AppUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;

    public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
    }


    @Override
    public AppUser addNewUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole addNewRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public AppUser updateUser(Long id, AppUser appUser) {
        AppUser updatedAppUser = appUserRepository.findById(id).orElseThrow();
        if(appUser.getUsername() != null) updatedAppUser.setUsername(appUser.getUsername());
        if(appUser.getEmail() != null) updatedAppUser.setEmail(appUser.getEmail());
        if(!appUser.getAppRoles().isEmpty()) updatedAppUser.setAppRoles(appUser.getAppRoles());
        appUserRepository.save(updatedAppUser);
        return updatedAppUser;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        appUser.getAppRoles().add(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public AppUser getUserById(Long id) {
        return appUserRepository.findById(id).orElse(null);
    }

    @Override
    public AppRole getRoleByroleName(String roleName) {
        return appRoleRepository.findByRoleName(roleName);
    }

    @Override
    public AppRole getRoleById(Long id) {
        return appRoleRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteUser(Long id) {
        appUserRepository.deleteById(id);
    }


    @Override
    public List<AppUser> listUsers() {
        return appUserRepository.findAll();
    }
}
