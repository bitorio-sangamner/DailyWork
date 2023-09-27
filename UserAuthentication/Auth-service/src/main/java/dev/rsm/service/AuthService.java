package dev.rsm.service;

import dev.rsm.dtos.UserCredentialsSaveRequest;
import dev.rsm.exceptions.UsernameAlreadyExistException;
import dev.rsm.model.UserCredentials;
import dev.rsm.repository.UserCredentialsRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    private final UserCredentialsRepository userCredentialsRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RestTemplate restTemplate;

    public AuthService(UserCredentialsRepository userCredentialsRepository, PasswordEncoder passwordEncoder, JwtService jwtService, RestTemplate restTemplate) {
        this.userCredentialsRepository = userCredentialsRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.restTemplate = restTemplate;
    }

    public String register(UserCredentialsSaveRequest userCredentialsSaveRequest) {
        String password = passwordEncoder.encode(userCredentialsSaveRequest.password());
        String username = userCredentialsSaveRequest.username();
        String email = userCredentialsSaveRequest.email();
        if (userCredentialsRepository.findByUsername(username).isEmpty()) {
            throw new UsernameAlreadyExistException("Username already exists.");
        }
        if (userCredentialsRepository.findByEmail(email).isEmpty()) {
            throw new RuntimeException("Email already registered.");
        }
        UserCredentials user = UserCredentials.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();

        UserCredentialsSaveRequest userServiceRegistrationResponse = new UserCredentialsSaveRequest(userCredentialsSaveRequest.username(), password, userCredentialsSaveRequest.email(), userCredentialsSaveRequest.firstName(), userCredentialsSaveRequest.lastName());

        String response = restTemplate.postForObject("http://localhost:8081/user/register", userServiceRegistrationResponse, String.class);
        System.out.println(response);
        userCredentialsRepository.save(user);
        return "User registered";
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
