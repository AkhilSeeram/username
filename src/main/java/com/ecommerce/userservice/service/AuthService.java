package com.ecommerce.userservice.service;

import com.ecommerce.userservice.DTOs.UserDTO;
import com.ecommerce.userservice.exceptions.UserAlreadyExistsException;
import com.ecommerce.userservice.models.Role;
import com.ecommerce.userservice.models.Session;
import com.ecommerce.userservice.models.SessionStatus;
import com.ecommerce.userservice.models.User;
import com.ecommerce.userservice.repositories.SessionRepository;
import com.ecommerce.userservice.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class AuthService {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private PasswordEncoder passwordEncoder;
    public AuthService(UserRepository userRepository,PasswordEncoder passwordEncoder,SessionRepository sessionRepository){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.sessionRepository=sessionRepository;
    }
    public UserDTO signup(String email, String password) throws UserAlreadyExistsException {
        Optional<User> userOptional=userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            throw new UserAlreadyExistsException("User with email " + email + " already exists");
        }
        User user=User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .roles(new HashSet<Role>())
                .build();
        return userRepository.save(user).toUserDTO();
    }
    public void logout(String token){
        Optional<Session> sessionOptional=sessionRepository.findByToken(token);
        Session session = sessionOptional.get();
        session.setSessionStatus(SessionStatus.LOGGED_OUT);
        sessionRepository.save(session);
    }
}
