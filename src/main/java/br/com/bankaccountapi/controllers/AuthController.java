package br.com.bankaccountapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bankaccountapi.services.auth.AuthService;
import br.com.bankaccountapi.services.auth.JwtResponse;
import br.com.bankaccountapi.services.auth.LoginRequest;
import br.com.bankaccountapi.services.auth.MessageResponse;
import br.com.bankaccountapi.services.auth.SignupRequest;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(
            @Valid @RequestBody LoginRequest loginRequest) {

        JwtResponse jwtResponse = authService.authenticateUser(
                loginRequest.getUsername(), loginRequest.getPassword());

        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody SignupRequest signupRequest) {

        authService.registerUser(
                signupRequest.getUsername(),
                signupRequest.getPassword(),
                signupRequest.getRoles());

        return ResponseEntity.ok(new MessageResponse(
                "User registered successfully!"));
    }


}