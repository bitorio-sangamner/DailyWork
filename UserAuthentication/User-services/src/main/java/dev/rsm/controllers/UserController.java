package dev.rsm.controllers;

import dev.rsm.dtos.response.ApplicationResponse;
import dev.rsm.service.UserService;
import dev.rsm.dtos.LoginCredentials;
import dev.rsm.dtos.UserRegistrationRequest;
import dev.rsm.dtos.UserUpdateRequest;
import dev.rsm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApplicationResponse> register(@RequestBody UserRegistrationRequest userRegistrationRequest){
        return userService.register(userRegistrationRequest);
    }

    @PostMapping("/update")
    public ResponseEntity<ApplicationResponse> updateById(@RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.updateUser(userUpdateRequest);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<User> retrieveUserById(@PathVariable("id") int id) {
        return userService.retrieveUserById(id);
    }

    @GetMapping("/retrieve")
    public ResponseEntity<List<User>> retrieveALlUser() {
        return userService.retrieveAllUser();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable("id") int id) {
        userService.deleteUserById(id);
    }

    @PostMapping("/login")
    public ResponseEntity<ApplicationResponse> login(@RequestBody LoginCredentials loginCredentials) {
        return userService.login(loginCredentials);
    }

    @PostMapping("/test/{message}")
    public ResponseEntity<ApplicationResponse> test(@PathVariable("message") String message) {
        return userService.test(message);
    }

}
