package com.user.service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "micro_users")
public class User {

    @Id
    private String userId;


    @Column(name = "NAME",length = 30)
    @NotNull
    private String name;


    @Column(name = "EMAIL")
    @Email
    private String email;


    @Column(name = "ABOUT")
    @NotNull
    private String about;


    @Column(name = "PASSWORD")
    @NotNull
    private String password;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @Transient
    private List<Rating> ratings=new ArrayList<>();
}
