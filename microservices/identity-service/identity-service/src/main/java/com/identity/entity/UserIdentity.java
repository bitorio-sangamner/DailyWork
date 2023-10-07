package com.identity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserIdentity {

    @Id
    private String userId;


    @Column(name = "NAME",length = 30)
    @NotBlank(message = "Name is mandatory")
    @Size(min = 4,message = "name must be min of 4 characters")
    private String name;


    @Column(name = "EMAIL")
    @Email(message="Email address is not valid!!")
    private String email;


    @Column(name = "ABOUT")
    @NotEmpty
    private String about;


    @Column(name = "PASSWORD")
    @NotEmpty
    @Size(min=3,max=100,message="Password must be min of 3 chars and max of 10 chars!!")
    private String password;

    private boolean active;
    private String otp;
    private LocalDateTime otpGeneratedTime;
}
