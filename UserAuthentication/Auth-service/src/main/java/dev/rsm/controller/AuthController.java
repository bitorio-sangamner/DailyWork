package dev.rsm.controller;

import dev.rsm.dtos.AuthRequest;
import dev.rsm.dtos.UserCredentialsSaveRequest;
import dev.rsm.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserCredentialsSaveRequest userCredentialsSaveRequest) {
        return authService.saveUser(userCredentialsSaveRequest);
    }

    @PostMapping("/token")
    public String generateToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));
        if (authentication.isAuthenticated()) {
            return authService.generateToken(authRequest.username());
        } else {
            throw new RuntimeException("Invalid access. ");
        }
    }

    @PostMapping("/validate/{token}")
    public String validateToken(@PathVariable String token) {
        authService.validateToken(token);
        return "Token is valid";
    }
}
