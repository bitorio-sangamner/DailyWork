package com.identity.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ABOUT")
    private String about;

    @Column(name = "PASSWORD")
    private String password;
}
