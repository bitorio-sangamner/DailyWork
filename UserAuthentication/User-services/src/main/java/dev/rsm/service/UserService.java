package dev.rsm.service;

import dev.rsm.dtos.LoginCredentials;
import dev.rsm.dtos.UserPasswordResetRequest;
import dev.rsm.dtos.UserRegistrationRequest;
import dev.rsm.dtos.UserUpdateRequest;
import dev.rsm.dtos.response.ApplicationResponse;
import dev.rsm.dtos.response.ResetEmailResponse;
import dev.rsm.exception.EmailException;
import dev.rsm.exception.LoginException;
import dev.rsm.exception.UserException;
import dev.rsm.model.User;
import dev.rsm.repos.UserRepository;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import net.bytebuddy.utility.RandomString;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final KafkaTemplate<String,Object> template;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, KafkaTemplate<String, Object> template) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.template = template;
    }

    public ResponseEntity<ApplicationResponse> register(UserRegistrationRequest userRegistrationRequest) {

        boolean userIsPresent = userRepository.findByUsernameAndPasswordAndEmail(userRegistrationRequest.username(),
                userRegistrationRequest.password(),
                userRegistrationRequest.email())
                .isPresent();
        if (userIsPresent) {
            throw new UserException("User data already registered.", 101);
        }
        User user = User.builder()
                .firstName(userRegistrationRequest.firstName())
                .lastName(userRegistrationRequest.lastName())
                .username(userRegistrationRequest.username())
                .password(userRegistrationRequest.password())
                .email((userRegistrationRequest.email()))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userRepository.save(user);
        return new ResponseEntity<>(new ApplicationResponse("Success", LocalDateTime.now()), HttpStatus.CREATED);
    }


    public ResponseEntity<ApplicationResponse> updateUser(UserUpdateRequest userUpdateRequest) {
        System.out.println(userUpdateRequest.id());
        User user = userRepository.findUserById(userUpdateRequest.id()).orElseThrow(() -> new UserException("User does not exist.", 102));
        System.out.println(user.toString());
        User updatedUser = User.builder()
                                .id(user.getId())
                                .firstName(userUpdateRequest.firstName())
                                .lastName(userUpdateRequest.lastName())
                                .username(userUpdateRequest.username())
                                .password(userUpdateRequest.password())
                                .email(userUpdateRequest.email())
                                .resetPasswordToken(null)
                                .createdAt(user.getCreatedAt())
                                .updatedAt(LocalDateTime.now())
                                .build();
        System.out.println(updatedUser.toString());
        userRepository.save(updatedUser);
        return new ResponseEntity<>(new ApplicationResponse("Updated user successfully.", LocalDateTime.now()), HttpStatus.OK);
    }

    public ResponseEntity<List<User>> retrieveAllUser() {
        List<User> userList = (List<User>) userRepository.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    public ResponseEntity<User> retrieveUserById(int id) {
        User user = userRepository.findUserById(id).orElseThrow(() -> new UserException("User does not exist.", 102));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    public ResponseEntity<ApplicationResponse> login(LoginCredentials loginCredentials) {
        User user = userRepository.findUserByUsername(loginCredentials.username()).orElseThrow(() -> new LoginException("Please enter correct username.", 103));
        boolean isMatch = passwordEncoder.matches(loginCredentials.password(), user.getPassword());
        if (!isMatch) {
            throw new LoginException("Please enter correct password.", 102);
        }
        return new ResponseEntity<>(new ApplicationResponse("Login successful", LocalDateTime.now()), HttpStatus.OK);
    }

    public ResponseEntity<ApplicationResponse> requestResetPasswordToken(String email) throws MessagingException, UnsupportedEncodingException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EmailException("Please enter correct email.", 104));
        String token = RandomString.make(10);
        user.setResetPasswordToken(token);
        userRepository.save(user);
        if (emailResetPasswordToken(token, email)) {
            return new ResponseEntity<>(new ApplicationResponse("Reset password token generated successfully.", LocalDateTime.now()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApplicationResponse("Please try again.", LocalDateTime.now()), HttpStatus.OK);
    }

    private boolean emailResetPasswordToken(String token, String recipientEmail) throws MessagingException, UnsupportedEncodingException {
        try {
            ResetEmailResponse response = new ResetEmailResponse(token, recipientEmail);
            CompletableFuture<SendResult<String, Object>> future = template.send("reset-password-email", response);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("Reset password token email generated successfully: {}", result);
                } else {
                    log.error("Reset password token email not generated successfully: {} -> ex: {}", result, ex);
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public ResponseEntity<ApplicationResponse> resetPassword(UserPasswordResetRequest userPasswordResetRequest) {
        User user = userRepository.findByResetPasswordTokenAndEmail(userPasswordResetRequest.resetPasswordToken(), userPasswordResetRequest.email()).orElseThrow(() -> new EmailException("Please enter correct email.", 1035));
        user.setPassword(passwordEncoder.encode(userPasswordResetRequest.password()));
        user.setResetPasswordToken(null);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        return new ResponseEntity<>(new ApplicationResponse("Reset password successfully.", LocalDateTime.now()), HttpStatus.OK);
    }

    public ResponseEntity<ApplicationResponse> test(String message) {
        CompletableFuture<SendResult<String, Object>> future = template.send("test", message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                log.error("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
        });
        return new ResponseEntity<>(new ApplicationResponse("Test successfully.", LocalDateTime.now()), HttpStatus.OK);
    }
}
