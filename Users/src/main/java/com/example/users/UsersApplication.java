package com.example.users;

import com.example.users.entities.Role;
import com.example.users.entities.User;
import com.example.users.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class UsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UserService userService) {
        return args -> {
            User user1 = new User();
            user1.setUserName("user1");
            user1.setPassword("123456");
            userService.addNewUser(user1);

            User user2 = new User();
            user2.setUserName("admin");
            user2.setPassword("123456");
            userService.addNewUser(user2);

            Stream.of("STUDENT", "ADMIN", "USER").forEach(r -> {
                Role role = new Role();
                role.setRoleName(r);
                userService.addNewRole(role);
            });

            userService.addRoleToUser("user1", "STUDENT");
            userService.addRoleToUser("user1", "USER");
            userService.addRoleToUser("admin", "ADMIN");

            try{
                User user = userService.authenticate("user1", "123456");
                System.out.println(user.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

        };
    }

}
