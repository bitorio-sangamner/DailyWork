package dev.rsm.service;

import dev.rsm.dtos.UserCredentialsSaveRequest;
import dev.rsm.exceptions.EmailAlreadyTakenException;
import dev.rsm.exceptions.UsernameAlreadyExistException;
import dev.rsm.model.UserCredentials;
import dev.rsm.repository.UserCredentialsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
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
        if (userCredentialsRepository.findByUsername(username).isPresent()) {
            log.error("User registration with username already registered: {}", userCredentialsSaveRequest);
            throw new UsernameAlreadyExistException("Username already exists.", HttpStatus.CONFLICT, 201);
        }
        if (userCredentialsRepository.findByEmail(email).isPresent()) {
            log.error("User registration with email already taken: {}", userCredentialsSaveRequest);
            throw new EmailAlreadyTakenException("Email already registered.", HttpStatus.CONFLICT, 202);
        }
        UserCredentials user = UserCredentials.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();

        UserCredentialsSaveRequest userServiceRegistrationResponse = new UserCredentialsSaveRequest(userCredentialsSaveRequest.username(), password, userCredentialsSaveRequest.email(), userCredentialsSaveRequest.firstName(), userCredentialsSaveRequest.lastName());
        log.info("User registration information send to USER-SERVICE: {}", userServiceRegistrationResponse);
        String response = restTemplate.postForObject("http://localhost:8081/user/register", userServiceRegistrationResponse, String.class);
        log.info("USER-SERVICE response for User Registration: {}", response);;
        userCredentialsRepository.save(user);
        log.info("User registered: {}", userCredentialsSaveRequest);
        return "User registered";
    }

    public String generateToken(String username) {
        log.info("JWT token generated for User with username: {}", username);
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
