package org.nastya.auth.dto;

import lombok.Value;

@Value
public class AuthRequest {
    String username;
    String password;
}