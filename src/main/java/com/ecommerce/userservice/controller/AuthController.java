package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.DTOs.SignupRequestDTO;
import com.ecommerce.userservice.DTOs.UserDTO;
import com.ecommerce.userservice.exceptions.UserAlreadyExistsException;
import com.ecommerce.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@RequestBody SignupRequestDTO signupRequestDTO) throws UserAlreadyExistsException {
        UserDTO userDTO = authService.signup(signupRequestDTO.getEmail(), signupRequestDTO.getPassword());
        return ResponseEntity.ok(userDTO);
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.split(" ")[1];
        authService.logout(token);
        return ResponseEntity.ok("Logged Out");
    }
}
