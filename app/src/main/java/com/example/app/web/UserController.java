package com.example.app.web;

import com.example.app.entity.AppRole;
import com.example.app.entity.AppUser;
import com.example.app.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserController {
    private AccountService accountService;

    @GetMapping("/users")
    public List<AppUser> getUsers() {
        return accountService.listUsers();
    }

    @GetMapping("/users/{id}")
    public AppUser getUserById(@PathVariable Long id) {
        return accountService.getUserById(id);
    }

    @PostMapping("/users")
    public ResponseEntity saveUser(@RequestBody AppUserRequest appUserRequest) {
        AppUser appUser = new AppUser();
        appUser.setUsername(appUserRequest.getUsername());
        appUser.setEmail(appUserRequest.getEmail());
        appUser.setPassword(appUserRequest.getPassword());
        appUser.getAppRoles().add(accountService.getRoleByroleName("USER"));
        accountService.addNewUser(appUser);
        return ResponseEntity.ok(AppUserResponse.builder()
                        .message("success")
                        .appUser(appUser)
                        .build());
    }

    @PutMapping ("/updateUser/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody AppUser appUser) {
        AppUser updatedAppUser = accountService.updateUser(id, appUser);
        return ResponseEntity.ok(AppUserResponse.builder()
                        .appUser(updatedAppUser)
                        .message("success")
                        .build());
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        accountService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("addRoleToUser")
    public ResponseEntity addRoleToUser(@RequestBody AddRoleToUserRequest addRoleToUserRequest) {
        accountService.addRoleToUser(addRoleToUserRequest.getUsername(), addRoleToUserRequest.getRoleName());
        AppUser appUser = accountService.loadUserByUsername(addRoleToUserRequest.getUsername());
        return ResponseEntity.ok(AppUserResponse.builder()
                        .message("success")
                        .appUser(appUser)
                        .build());
    }

}
