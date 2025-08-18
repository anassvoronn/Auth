package org.nastya.auth.controller;

import lombok.RequiredArgsConstructor;
import org.nastya.auth.dto.AuthRequest;
import org.nastya.auth.dto.AuthResponse;
import org.nastya.auth.dto.SignUpRequest;
import org.nastya.auth.service.AuthService;
import org.nastya.auth.service.handler.UserEventHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserEventHandler userEventHandler;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody SignUpRequest singUpRequest) {
        userEventHandler.handleAfterUserCreate(authService.register(singUpRequest));
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        AuthResponse response = authService.login(authRequest);
        return ResponseEntity.ok(response);
    }
}