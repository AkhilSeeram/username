package com.ecommerce.userservice.DTOs;

import com.ecommerce.userservice.models.Role;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
public class UserDTO {
    private String email;
    private Set<Role> roles;
}
