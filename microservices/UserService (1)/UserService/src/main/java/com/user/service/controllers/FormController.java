package com.user.service.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/forget")
public class FormController {

    @GetMapping("/reset")
    public String setPassword()
    {
        System.out.println("inside reset");
        return "form.html";
    }
}
