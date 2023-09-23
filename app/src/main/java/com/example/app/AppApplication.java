package com.example.app;

import com.example.app.entity.AppRole;
import com.example.app.entity.AppUser;
import com.example.app.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountService accountService) {
        return args -> {
            accountService.addNewRole(new AppRole(null, "USER"));
            accountService.addNewRole(new AppRole(null, "ADMIN"));
            accountService.addNewRole(new AppRole(null, "CUSTOMER_MANAGER"));
            accountService.addNewRole(new AppRole(null, "PRODUCT_MANAGER"));
            accountService.addNewRole(new AppRole(null, "BILLS_MANAGER"));

            accountService.addNewUser(new AppUser(null, "user1", "user1@gmail.com", "1234", new ArrayList<>()));
            accountService.addNewUser(new AppUser(null, "user2", "user2@gmail.com", "1234", new ArrayList<>()));
            accountService.addNewUser(new AppUser(null, "user3", "user3@gmail.com", "1234", new ArrayList<>()));
            accountService.addNewUser(new AppUser(null, "user4", "user4@gmail.com", "1234", new ArrayList<>()));
            accountService.addNewUser(new AppUser(null, "admin", "admin@gmail.com", "1234", new ArrayList<>()));

            accountService.addRoleToUser("user1", "USER");
            accountService.addRoleToUser("admin", "USER");
            accountService.addRoleToUser("admin", "ADMIN");
            accountService.addRoleToUser("user2", "USER");
            accountService.addRoleToUser("user2", "CUSTOMER_MANAGER");
            accountService.addRoleToUser("user3", "USER");
            accountService.addRoleToUser("user3", "PRODUCT_MANAGER");
            accountService.addRoleToUser("user4", "USER");
            accountService.addRoleToUser("user4", "BILLS_MANAGER");

            /*accountService.listUsers().forEach(user -> {
                System.out.println(user.getUsername());
                System.out.println(user.getEmail());
                System.out.println(user.getAppRoles());
            });*/
        };
    }

}
