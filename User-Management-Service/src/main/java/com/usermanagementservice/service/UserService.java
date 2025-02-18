package com.usermanagementservice.service;

import com.usermanagementservice.dto.ApiResponseDto;
import com.usermanagementservice.dto.ChangePwdDto;
import com.usermanagementservice.dto.UserDto;
import com.usermanagementservice.exception.UserAlreadyExistException;
import org.springframework.http.ResponseEntity;

public interface UserService {
    boolean registerUser(UserDto userDto) throws UserAlreadyExistException;
    boolean verifyOtp(String email, String otp);
    boolean resetPassword(String email, String otp, String newPassword);
    String changePassword(ChangePwdDto changePwdDto);

}
