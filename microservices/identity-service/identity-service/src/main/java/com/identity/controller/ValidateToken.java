package com.identity.controller;

import com.identity.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authanticate")
public class ValidateToken {

    @Autowired
    private AuthService authService;

    @GetMapping("/validate/{token}")
    public String validToken(@PathVariable("token") String token)
    {
        System.out.println("inside validate method");
        String msg="";
        Boolean flag=authService.validateToken(token);

        if(flag)
        {
            msg="token validate!!";
            return msg;
        }

        msg="token is not valid";


        return msg;
    }
}
