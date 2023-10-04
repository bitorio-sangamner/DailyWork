package com.email.Email_Sending_App2.controller;

import com.email.Email_Sending_App2.service.EmailService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PutMapping("/forgot/{email}")
    public ResponseEntity<Object> forgetPassword(@PathVariable String email)
    {
        boolean flag=emailService.forgotPassword(email);
        String token = RandomString.make(30);

        if(flag)
        {
            String message="Please check your email to set new password to your account";
            return new ResponseEntity(message,HttpStatus.OK);
        }
       return new ResponseEntity("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
