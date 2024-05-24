package com.example.lms_system.security.auth;

import com.example.lms_system.security.password.reset.PasswordResetRequest;
import com.example.lms_system.security.verification.VerificationToken;
import com.example.lms_system.security.verification.VerificationTokenRepository;
import com.example.lms_system.user.UserRepository;
import com.example.lms_system.user.UserService;
import com.example.lms_system.user.Users;
import com.example.lms_system.utils.events.RegistrationCompleteEvent;
import com.example.lms_system.utils.events.listener.RegistrationCompleteListener;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final UserRepository userRepo;
    private final AuthenticationService service;
    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository tokenRepository;
    private final RegistrationCompleteListener listener;
//    private final AuthenticationService authService;
//    @PostMapping("/register")
//    ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
//        return ResponseEntity.ok(service.record(request));
//    }
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegisterRequest registrationRequest, final HttpServletRequest request) throws Exception {
        Users user = service.registerUser(registrationRequest);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return ResponseEntity.ok(service.record(registrationRequest));
    }
    @PostMapping("/login")
    ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest credentials){
        return ResponseEntity.ok(service.authenticate(credentials));
    }
    //    @PostMapping("/resetPassword")
    @GetMapping("/verifyEmail")
    public String sendVerificationToken(@RequestParam("token") String token){
        VerificationToken theToken = tokenRepository.findByToken(token);
        if (theToken.getUser().isEnabled()){
            return "This account has already been verified, please, login.";
        }
        String verificationResult = userService.validateToken(token);
        if (verificationResult.equalsIgnoreCase("valid")){
            return "Email verified successfully. Now you can login to your account";
        }
        return "Invalid verification link, please, check your email for new verification link";
    }

    @GetMapping("/resend-verification-token")
    public String resendVerificationToken(@RequestParam("token") String oldToken, HttpServletRequest request)
            throws MessagingException, UnsupportedEncodingException {
        VerificationToken verificationToken =  userService.generateNewVerificationToken(oldToken);
        System.out.println(verificationToken);
        Users theUser = verificationToken.getUser();
        resendVerificationTokenEmail(theUser, applicationUrl(request), verificationToken);
        return "A new verification link has been sent to your email," +
                " please, check to complete your registration";
    }
    @PostMapping("/password-reset-request")
    public String resetPasswordRequest(@RequestBody PasswordResetRequest passwordResetRequest, final HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        Optional<Users> user = userRepo.findByEmail(passwordResetRequest.getEmail());
        String passwordResetUrl = "";
        if (user.isPresent()){
            String passwordResetToken = UUID.randomUUID().toString();
             userService.createPasswordResetToken(user,passwordResetToken);
             passwordResetUrl = passwordResetEmailLink(user.get(), applicationUrl(request),passwordResetToken);
        }
        return passwordResetUrl;
    }
    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody PasswordResetRequest resetRequest, @RequestParam("token") String resetToken){
        String tokenValidationResult = userService.validatePasswordResetToken(resetToken);
        if (!tokenValidationResult.equalsIgnoreCase("valid")){
            return "Invalid Password reset Token";
        }
        Users user = userService.findByResetPasswordToken(resetToken);
        if (user != null){
            userService.resetUserPassword(user, resetRequest.getNewPassword());
        }
        return "Invalid password reset token";
    }
    private String passwordResetEmailLink(Users users, String applicationUrl, String passwordResetToken) throws MessagingException, UnsupportedEncodingException {
        String url = applicationUrl+"/reset-password?token="+passwordResetToken;
        listener.sendResetPasswordEmail(url);
        log.info("Reset password link: {}",url);
        return url;
    }

    private void resendVerificationTokenEmail(Users theUser, String applicationUrl, VerificationToken token)
            throws MessagingException, UnsupportedEncodingException {
        String url = applicationUrl+"/register/verifyEmail?token="+token.getToken();
        listener.sendVerificationEmail(url);
        log.info("Click the link to verify your registration :  {}", url);
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"
                +request.getServerPort()+request.getContextPath();
    }

}
