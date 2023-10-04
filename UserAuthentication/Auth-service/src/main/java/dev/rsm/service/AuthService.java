package dev.rsm.service;

import com.alibaba.fastjson.JSONObject;
import dev.rsm.dtos.ApplicationResponse;
import dev.rsm.dtos.UserCredentialsSaveRequest;
import dev.rsm.exceptions.EmailAlreadyTakenException;
import dev.rsm.exceptions.UsernameAlreadyExistException;
import dev.rsm.model.UserCredentials;
import dev.rsm.repository.UserCredentialsRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

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

    public ResponseEntity<ApplicationResponse> register(UserCredentialsSaveRequest userCredentialsSaveRequest) throws Exception {
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
        HttpHeaders headers = new HttpHeaders();
        headers.set("REQUEST-FOR", "user");
        HttpEntity<UserCredentialsSaveRequest> entity = new HttpEntity<>(userServiceRegistrationResponse, headers);
        JSONObject response = restTemplate.postForObject("lb://USER-SERVICE/user/register", entity, JSONObject.class);
        log.info("USER-SERVICE response for User Registration: {}", response);
        if (response == null) {
            throw new Exception();
        }

        if (response.containsKey("errorCode")) {
            log.error("Error");
            throw new Exception();
        }

        userCredentialsRepository.save(user);
        log.info("User registered: {}", userCredentialsSaveRequest);
        return new ResponseEntity<>(new ApplicationResponse("User registered", LocalDateTime.now()), HttpStatus.CREATED);
    }

    public ResponseEntity<ApplicationResponse> generateToken(String username) {
        log.info("JWT token generated for User with username: {}", username);
        return new ResponseEntity<>(new ApplicationResponse(jwtService.generateToken(username), LocalDateTime.now()), HttpStatus.OK);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
