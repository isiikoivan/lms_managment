package com.example.lms_system.user;

import com.example.lms_system.security.password.reset.PasswordResetRepository;
import com.example.lms_system.security.password.reset.PasswordResetService;
import com.example.lms_system.security.verification.VerificationToken;
import com.example.lms_system.security.verification.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordResetRepository repository;
    private final VerificationTokenRepository tokenRepository;
    private final UserRepository repo;
    private final PasswordResetService service;
    private final PasswordEncoder passwordEncoder;
//    public void createPasswordResetTokenForUser(Users user, String token) {
//        PasswordResetToken myToken = new PasswordResetToken(null,token, user, new Date(System.currentTimeMillis()+1000*60*24));
//        repository.save(myToken);
//    }
    public void saveUserVerificationToken(Users theUser, String token) {
        var verificationToken = new VerificationToken(token, theUser);
        tokenRepository.save(verificationToken);
    }
    public String validateToken(String theToken) {
        VerificationToken token = tokenRepository.findByToken(theToken);
        if(token == null){
            return "Invalid verification token";
        }
        Users user = token.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((token.getExpirationTime().getTime()-calendar.getTime().getTime())<= 0){
            return "Verification link already expired," +
                    " Please, click the link below to receive a new verification link";
        }
        user.setVerified(true);
        repo.save(user);
        return "validated";
    }

    public VerificationToken generateNewVerificationToken(String oldToken) {
        VerificationToken verificationToken = tokenRepository.findByToken(oldToken);
        var tokenExpirationTime = new VerificationToken();
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationToken.setExpirationTime(tokenExpirationTime.getTokenExpirationTime());
        return tokenRepository.save(verificationToken);
    }

    public void createPasswordResetToken(Optional<Users> user, String passwordResetToken) {
        service.passwordResetToken(user.get(),passwordResetToken);
    }

    public String validatePasswordResetToken(String resetToken) {
        return service.validateToken(resetToken);
    }

    public Users findByResetPasswordToken(String resetToken) {
        return service.findUserByPasswordToken(resetToken).get();
    }

    public void resetUserPassword(Users user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
    }
}
