package com.example.emailverification.user;

import com.example.emailverification.exceptions.UserAlreadyExistsException;
import com.example.emailverification.registration.RegistrationRequest;
import com.example.emailverification.registration.token.VerificationToken;
import com.example.emailverification.registration.token.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(RegistrationRequest request) {
        Optional<User> user = userRepository.findByEmail(request.email());
        if(user.isPresent()) {
            throw new UserAlreadyExistsException(
                    "User with email "+request.email()+"already exist");
        }
        User newUser = new User();
        newUser.setFirstName(request.firstName());
        newUser.setLastName(request.lastName());
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setRole(request.role());
        return userRepository.save(newUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUserVerificationToken(User user, String token) {
        var verificationToken = new VerificationToken(token, user);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String validateToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (token == null) return "Invalid verification token";
        Calendar calendar = Calendar.getInstance();
        if((verificationToken.getExpirationTime().getTime()-calendar.getTime().getTime())<=0) {
            verificationTokenRepository.delete(verificationToken);
            return "Token already Expired";
        }
        User user = verificationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        return "Email verified successfully. Now you can login to your account";
    }
}
