package com.example.app.web;

import com.example.app.entity.AppUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class AppUserResponse {
    private String message;
    private AppUser appUser;
}
