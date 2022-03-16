package br.com.bankaccountapi.controllers;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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


@Api(value="API REST aothentication")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @ApiOperation(value="Authenticates a user and gives access to queries")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(
            @Valid @RequestBody LoginRequest loginRequest) {

        JwtResponse jwtResponse = authService.authenticateUser(
                loginRequest.getUsername(), loginRequest.getPassword());

        return ResponseEntity.ok(jwtResponse);
    }

    @ApiOperation(value="Register a user")
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