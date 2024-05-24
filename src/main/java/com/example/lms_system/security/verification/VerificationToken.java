package com.example.lms_system.security.verification;

import com.example.lms_system.user.Users;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class VerificationToken {
    @Id
    @GeneratedValue
    private Long id;
    private String token;
    private Date expirationTime;
    private static final int EXPIRATION_TIME=1;
    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public VerificationToken(String token, Users user) {
        super();
        this.token = token;
        this.user = user;
        this.expirationTime = this.getTokenExpirationTime();
    }

    public VerificationToken(String token) {
        super();
        this.token = token;
        this.expirationTime = this.getTokenExpirationTime();
    }

    public Date getTokenExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
        return new Date(calendar.getTime().getTime());
    }
}
