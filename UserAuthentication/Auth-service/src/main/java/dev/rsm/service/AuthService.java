package dev.rsm.service;

import dev.rsm.dtos.UserCredentialsSaveRequest;
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

    public String saveUser(UserCredentialsSaveRequest userCredentialsSaveRequest) {
        String password = passwordEncoder.encode(userCredentialsSaveRequest.password());
        UserCredentials user = UserCredentials.builder()
                .name(userCredentialsSaveRequest.firstName() + " " + userCredentialsSaveRequest.lastName())
                .username(userCredentialsSaveRequest.username())
                .email(userCredentialsSaveRequest.email())
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
