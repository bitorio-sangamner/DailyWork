package com.user.service.controllers;

import com.user.service.entity.User;
import com.user.service.impl.UserServiceImpl;
import com.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger= LoggerFactory.getLogger(UserController.class);
    //create

//    @PostMapping("/create")
//    public ResponseEntity<User> createUser(@RequestBody User user)
//    {
//        User userObj=userService.saveUser(user);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(userObj);
//    }

    //get single user
    @GetMapping("/{userId}")
    @CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId)
    {
        User user=userService.getUser(userId);

      System.out.println("Hii");
        return ResponseEntity.ok(user);
    }

    //creating fall back method for circuitbreaker
    public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex)
    {
        logger.info("Fallback is executed because service is down : ",ex.getMessage());
       User user= User.builder()
                .email("dummy@gmail.com")
                .name("dummy")
                .about("This user is created dummy because some service is down")
                .userId("12345")
                .build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    //get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> allUsers=userService.getAllUser();

        return ResponseEntity.ok(allUsers);
    }

//    @GetMapping("/{email}")
//    public User findUserByEmail(@PathVariable String email)
//    {
//        User userObj=userService.findUserByEmail(email);
//
//        return userObj;
//    }
}
