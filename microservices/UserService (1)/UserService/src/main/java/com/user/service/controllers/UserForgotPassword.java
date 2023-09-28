package com.user.service.controllers;

import com.user.service.entity.User;
import com.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserForgotPassword {

    @Autowired
    private UserService userService;
    @GetMapping("/forgot/{email}")
    public User getUserByEmail(@PathVariable String email)
    {
        User userObj=userService.findUserByEmail(email);

        System.out.println("Hello");

        return userObj;
    }

    @GetMapping("/details/{email}")
    public User findUserByEmail(@PathVariable String email)
    {
        User userObj=userService.findUserByEmail(email);

        return userObj;
    }


   @PutMapping("/forgetPasswordForm")
    public ResponseEntity<String> forgetPasswordForm(@RequestParam String email, @RequestHeader String newPassword)
    {
        return new ResponseEntity<>(userService.setPassword(email,newPassword), HttpStatus.OK);
    }


}
