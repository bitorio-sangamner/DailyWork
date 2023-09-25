package dev.rsm.controllers;

import dev.rsm.dtos.LoginCredentialsWithToken;
import dev.rsm.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/forgot")
public class ForgotPasswordController {

    private final UserService userService;

    @Autowired
    public ForgotPasswordController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/get_reset_password_token/{email}")
    public String requestResetPasswordToken(@PathVariable("email") String email) throws MessagingException, UnsupportedEncodingException {
        return userService.requestResetPasswordToken(email);
    }

    @PostMapping("/reset_password")
    public String resetPassword(@RequestBody LoginCredentialsWithToken loginCredentials) {
        return userService.resetPassword(loginCredentials);
    }
}
