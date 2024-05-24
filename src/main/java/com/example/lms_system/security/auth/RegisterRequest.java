package com.example.lms_system.security.auth;

import com.example.lms_system.roles.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String surName;
    private String otherNames;
//    private Roles role;
//    private Date createdAt;
    private String email;
    private String username;
    private String password;
}
