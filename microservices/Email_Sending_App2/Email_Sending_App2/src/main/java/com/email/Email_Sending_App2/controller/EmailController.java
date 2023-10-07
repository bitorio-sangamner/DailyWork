package com.email.Email_Sending_App2.controller;

import com.email.Email_Sending_App2.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.Random;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PutMapping("/forgot/{email}")
    public ResponseEntity<Object> forgetPassword(@PathVariable String email)
    {
        //boolean flag=emailService.forgotPassword(email);

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder(5);
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            randomString.append(randomChar);
        }
        String NewrandomToken=randomString.toString();
        System.out.println("random token"+NewrandomToken);

        boolean flag=emailService.updateResetPasswordToken(NewrandomToken, email);



        if(flag)
        {
            String message="Please check your email to set new password to your account";
            return new ResponseEntity(message,HttpStatus.OK);
        }
       return new ResponseEntity("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @PostMapping("/sendOtpEmail/{email}/{otp}")
//    public Boolean sendOtpEmail(@PathVariable("email")String email,@PathVariable("otp")String otp) throws MessagingException {
//        emailService.sendOtpEmail(email,otp);
//        return true;
    //}
}
