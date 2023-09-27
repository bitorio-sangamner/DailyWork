package dev.rsm.service;

import dev.rsm.dtos.LoginCredentials;
import dev.rsm.dtos.LoginCredentialsWithToken;
import dev.rsm.dtos.UserRegistrationRequest;
import dev.rsm.dtos.UserUpdateRequest;
import dev.rsm.model.User;
import dev.rsm.repos.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import net.bytebuddy.utility.RandomString;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JavaMailSender javaMailSender;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JavaMailSender javaMailSender) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
    }

    public String register(UserRegistrationRequest userRegistrationRequest) {
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
        return "Success";
    }


    public String updateUser(UserUpdateRequest userUpdateRequest) {
        System.out.println(userUpdateRequest.id());
        User user = userRepository.getUserById(userUpdateRequest.id());
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
        return "User update successfully";
    }

    public List<User> retrieveAllUser() {
        return (List<User>) userRepository.findAll();
    }

    public User retrieveUserById(int id) {
        return userRepository.getUserById(id);
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    public String login(LoginCredentials loginCredentials) {
        User user = userRepository.getUserByUsername(loginCredentials.username());
        boolean isMatch = passwordEncoder.matches(loginCredentials.password(), user.getPassword());
        if (isMatch) {
            return "Login successful";
        } else {
            return "Please enter correct credentials";
        }

    }

    public String requestResetPasswordToken(String email) throws MessagingException, UnsupportedEncodingException {
        User user = userRepository.findByEmail(email);
        String token = RandomString.make(10);
        if (user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Could not find any user with this email " + email);
        }
        emailResetPasswordToken(token, email);
        return "Reset password token generated successfully";
    }

    private void emailResetPasswordToken(String token, String recipientEmail) throws MessagingException, UnsupportedEncodingException {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom("noreply.com", "User Support");
            helper.setTo(recipientEmail);

            String subject = "Password Reset Token";

            String content = "<p>Hello,</p>"
                    + "<p>You have requested to reset your password.</p>"
                    + "<p>Below is your reset password token:</p>"
                    + "<p>" + token + "</p>"
                    + "<br>"
                    + "<p>Ignore this email if you have not requested for reset password, ";

            helper.setSubject(subject);

            helper.setText(content, true);

            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String resetPassword(LoginCredentialsWithToken loginCredentials) {
        User user = userRepository.findByResetPasswordTokenAndEmail(loginCredentials.resetPasswordToken(), loginCredentials.email());
        if (user != null) {
            user.setPassword(passwordEncoder.encode(loginCredentials.password()));
            user.setResetPasswordToken(null);
            user.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user);
        } else {
            throw new RuntimeException("Could not find any user with this email " + loginCredentials.email());
        }
        return "Reset password successfully";
    }
}
