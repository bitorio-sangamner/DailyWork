package com.user.service.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("form")
public class Form {

    @GetMapping("/resetPasswordForm")
    public String getResetForm()
    {
        System.out.println("Inside reset form controller");
      return "resetForm.html";
    }
}
