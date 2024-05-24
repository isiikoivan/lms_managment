package com.example.lms_system.security.password.reset;

import com.example.lms_system.security.verification.VerificationToken;
import com.example.lms_system.user.UserRepository;
import com.example.lms_system.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PasswordResetService {
    private final PasswordResetRepository passwordResetRepository;
    public void passwordResetToken(Users user, String passwordToken){
        PasswordResetToken passwordResetToken = new PasswordResetToken(passwordToken,user);
        passwordResetRepository.save(passwordResetToken);
    }
    public String validateToken(String theToken) {
        PasswordResetToken token = passwordResetRepository.findByToken(theToken);
        if(token == null){
            return "Invalid password reset token";
        }
        Users user = token.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((token.getExpirationTime().getTime()-calendar.getTime().getTime())<= 0){
            return "Link already expired.";
        }
        return "Valid";
    }
    public Optional<Users> findUserByPasswordToken(String token){
        return Optional.ofNullable(passwordResetRepository.findByToken(token).getUser());
    }
}
