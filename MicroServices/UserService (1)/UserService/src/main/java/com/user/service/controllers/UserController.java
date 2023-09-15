package com.user.service.controllers;

import com.user.service.entity.User;
import com.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

   @Autowired
    private UserService userService;
    //create

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        User userObj=userService.saveUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userObj);
    }

    //get single user
    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId)
    {
        User user=userService.getUser(userId);

        return ResponseEntity.ok(user);
    }

    //get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> allUsers=userService.getAllUser();

        return ResponseEntity.ok(allUsers);
    }
}
