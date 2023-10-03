package com.email.Email_Sending_App2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {


    private String userId;

    private String name;


    private String email;


    private String about;


    private String password;

    private String resetPasswordToken;
}
