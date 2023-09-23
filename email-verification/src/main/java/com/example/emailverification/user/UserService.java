package com.example.emailverification.user;

import com.example.emailverification.registration.RegistrationRequest;
import com.example.emailverification.registration.token.VerificationToken;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getUsers();
    User registerUser(RegistrationRequest request);
    Optional<User> findByEmail(String email);

    void saveUserVerificationToken(User user, String verificationToken);

    String validateToken(String token);
}

