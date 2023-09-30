package dev.rsm.controller;

import dev.rsm.dtos.ApplicationResponse;
import dev.rsm.dtos.AuthRequest;
import dev.rsm.dtos.UserCredentialsSaveRequest;
import dev.rsm.service.AuthService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApplicationResponse> addNewUser(@RequestBody UserCredentialsSaveRequest userCredentialsSaveRequest) throws Exception {
        return authService.register(userCredentialsSaveRequest);
    }

    @PostMapping("/token")
    public ResponseEntity<ApplicationResponse> generateToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));
        if (!authentication.isAuthenticated()) {
            throw new RuntimeException("Invalid access. ");
        }
        return authService.generateToken(authRequest.username());
    }

    @PostMapping("/validate/{token}")
    public String validateToken(@PathVariable String token) {
        authService.validateToken(token);
        return "Token is valid";
    }
}
