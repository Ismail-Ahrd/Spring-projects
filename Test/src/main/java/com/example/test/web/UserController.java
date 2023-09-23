package com.example.test.web;

import com.example.test.entity.AppRole;
import com.example.test.entity.AppUser;
import com.example.test.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/newUser")
    public AppUser addNewUser(@RequestBody AppUserRequest appUserRequest) {
        System.out.println("Hello from Post request");
        AppUser appUser = new AppUser();
        appUser.setUsername(appUserRequest.getUsername());
        appUser.setEmail(appUserRequest.getEmail());
        appUser.setPassword(appUserRequest.getPassword());
        appUser.setAppRoles(List.of(new AppRole(null, "User")));
        return accountService.addNewUser(appUser);
    }

}
