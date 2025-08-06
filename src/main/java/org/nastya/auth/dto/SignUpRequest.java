package org.nastya.auth.dto;

import lombok.Value;
import org.nastya.auth.entity.enums.Role;

@Value
public class SignUpRequest {
    String username;
    String password;
    String email;
    String firstName;
    String lastName;
    Role role;
}