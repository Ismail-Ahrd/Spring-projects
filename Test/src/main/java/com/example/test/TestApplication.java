package com.example.test;

import com.example.test.entity.AppRole;
import com.example.test.entity.AppUser;
import com.example.test.repository.AppRoleRepository;
import com.example.test.repository.AppUserRepository;
import com.example.test.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }


    }


