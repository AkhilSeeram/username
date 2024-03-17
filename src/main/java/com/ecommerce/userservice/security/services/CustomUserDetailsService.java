package com.ecommerce.userservice.security.services;

import com.ecommerce.userservice.models.User;
import com.ecommerce.userservice.repositories.UserRepository;
import com.ecommerce.userservice.security.models.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException(username + " not found");
        }
        return new CustomUserDetails(user.get());
    }
}