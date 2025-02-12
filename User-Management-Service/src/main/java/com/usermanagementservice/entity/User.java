package com.usermanagementservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isVerified;

    //Verification code for account activation
    private String verificationCode;
    private LocalDateTime tokenExpiration;


}
