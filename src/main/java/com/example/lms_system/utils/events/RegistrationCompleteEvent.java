package com.example.lms_system.utils.events;

import com.example.lms_system.user.Users;
import lombok.*;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
    private Users user;
    private String applicationUrl;
    public RegistrationCompleteEvent(Users user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
