package com.example.emailverification.registration;


import com.example.emailverification.event.RegistrationCompleteEvent;
import com.example.emailverification.registration.token.VerificationToken;
import com.example.emailverification.registration.token.VerificationTokenRepository;
import com.example.emailverification.user.User;
import com.example.emailverification.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository verificationTokenRepository;

    @PostMapping
    public String registerUser(@RequestBody RegistrationRequest registrationRequest, final HttpServletRequest request) {
        User user = userService.registerUser(registrationRequest);
        //publish registration event
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "Success! Please check you Email to complete your registration";
    }

    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token){
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if(verificationToken.getUser().isEnabled()) {
            return "This account has already been verifies, please login.";
        }
        String verificationResult = userService.validateToken(token);
        return verificationResult;
    }

    public String applicationUrl(HttpServletRequest request) {
        String serverName = request.getServerName();
        int port = request.getServerPort();
        String contextPath = request.getContextPath();
        return "http://" +serverName+":"+port+contextPath;
    }
}
