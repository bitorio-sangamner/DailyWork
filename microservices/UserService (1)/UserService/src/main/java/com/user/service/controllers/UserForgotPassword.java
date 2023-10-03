package com.user.service.controllers;

import com.alibaba.fastjson.JSONObject;
import com.user.service.entity.User;
import com.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
public class UserForgotPassword {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;
//    @GetMapping("/forgot/{email}")
//    public User getUserByEmail(@PathVariable String email)
//    {
//        try {
//            User userObj = userService.findUserByEmail(email);
//            User user = restTemplate.getForObject("http://IDENTITY-SERVICE/auth/findUserIdentityByEmail/" + email, User.class);
//
//            System.out.println("Hello");
//
//            return userObj;
//        }
//
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @GetMapping("/updateResetPasswordToken/{resetToken}/{email}")
    public User updateResetPasswordToken(@PathVariable("resetToken") String resetToken,@PathVariable("email") String email)
    {
        System.out.println("Inside updateResetPasswordToken");

        User userObj = userService.findUserByEmail(email);
        User user = restTemplate.getForObject("http://IDENTITY-SERVICE/auth/findUserIdentityByEmail/" + email, User.class);

        System.out.println("userObj :"+userObj.getEmail());
        System.out.println("user :"+user.getEmail());

        if (userObj != null && user!=null) {
            userObj.setResetPasswordToken(resetToken);
            userService.updateUser(userObj);
        } else {
            throw new RuntimeException("Could not find any customer with the email " + email);
        }
        return userObj;
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token) {
        User user = userService.getByResetPasswordToken(token);

        System.out.println("inside showResetPasswordForm :"+token);

        if (user == null) {
            return "invalid token";
        }

        return "reset_password_form";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(@RequestBody JSONObject json) {

        System.out.println("inside processResetPassword");
        //JSONObject jsonObject=new JSONObject();

        String token=json.getString("token");
        String password=json.getString("password");

        System.out.println("Token :"+token);
        User user=userService.getByResetPasswordToken(token);

        System.out.println("**********************************");
        System.out.println("User name :"+user.getName());
        System.out.println("User email :"+user.getEmail());
        System.out.println("User about :"+user.getAbout());
        if (user == null) {

            return "User not found!!";
        } else {
            userService.updatePassword(user, password);


        }

        return "password updated successfully!!";
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
        System.out.println("inside forgetPasswordForm");
        return new ResponseEntity<>(userService.setPassword(email,newPassword), HttpStatus.OK);
    }


}
