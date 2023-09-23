package com.example.test.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class AppUserRequest {
    private String username;
    private String email;
    private String password;
}
