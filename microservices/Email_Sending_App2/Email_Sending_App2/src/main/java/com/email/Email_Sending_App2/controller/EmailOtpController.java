package com.email.Email_Sending_App2.controller;

import com.email.Email_Sending_App2.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;


@RestController
@RequestMapping("/otp")
public class EmailOtpController {

    @Autowired
    private EmailService emailService;
//    @PostMapping("/sendOtpEmail/{email}/{otp}")
//    public Boolean sendOtpEmail(@PathVariable("email")String email, @PathVariable("otp")String otp) throws MessagingException {
//        emailService.sendOtpEmail(email,otp);
//        return true;
//    }

    @GetMapping ("/sendOtpEmail/{email}/{otp}")
    public boolean sendOtpEmail(@PathVariable("email")String email, @PathVariable("otp")String otp) throws MessagingException {

        System.out.println("inside sendOtpEmail controller");
        emailService.sendOtpEmail(email,otp);
        return true;
    }


}
