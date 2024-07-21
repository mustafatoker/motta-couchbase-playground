package com.mustafatoker.motta.auth.controller;

import com.mustafatoker.motta.auth.dto.request.LoginRequest;
import com.mustafatoker.motta.auth.dto.request.RegisterRequest;
import com.mustafatoker.motta.auth.dto.response.AuthResponse;
import com.mustafatoker.motta.auth.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest registerRequest) {
        authenticationService.registerUser(registerRequest.username(), registerRequest.password());

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        String token = authenticationService.authenticate(loginRequest.username(), loginRequest.password());

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
