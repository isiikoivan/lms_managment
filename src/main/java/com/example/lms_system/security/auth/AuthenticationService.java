package com.example.lms_system.security.auth;

import com.example.lms_system.roles.RoleRepository;
import com.example.lms_system.roles.Roles;
import com.example.lms_system.security.config.JwtService;
import com.example.lms_system.user.UserRepository;
import com.example.lms_system.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService service;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository repo;
    public AuthenticationResponse record(RegisterRequest request){
        var user = Users.builder()
                .surName(request.getSurName())
                .otherNames(request.getOtherNames())
                .email(request.getEmail())
                .username(request.getUsername())
                .verified(false)
                .role(new Roles(1L,"student",null,null))
                .password(passwordEncoder.encode(request.getPassword()))
                .createdAt(new Date(System.currentTimeMillis()))
                .build();
//        repository.save(user);
        var jwtToken = service.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = service.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public Users registerUser(RegisterRequest request) throws Exception {
        Optional<Users> user = this.repository.findByEmail(request.getEmail());
        if (user.isPresent()){
            throw new Exception("User with email already exists");
        }
        var newUser = new Users();
            newUser.setSurName(request.getSurName());
            newUser.setOtherNames(request.getOtherNames());
            newUser.setEmail(request.getEmail());
            newUser.setVerified(false);
            newUser.setUsername(request.getUsername());
            newUser.setRole(new Roles(1L,"student",null,null));
            newUser.setPassword(passwordEncoder.encode(request.getPassword()));
            newUser.setCreatedAt(new Date(System.currentTimeMillis()));
        return repository.save(newUser);
    }
}
