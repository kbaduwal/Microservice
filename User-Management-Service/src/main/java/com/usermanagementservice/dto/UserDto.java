package com.usermanagementservice.dto;

import lombok.Data;

@Data
public class UserDto {
    private String userName;
    private String email;
    private String phoneNumber;
    private String verificationCode;
    private String password;
}
