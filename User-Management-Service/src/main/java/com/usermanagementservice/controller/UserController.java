package com.usermanagementservice.controller;

import com.usermanagementservice.dto.ApiResponseDto;
import com.usermanagementservice.dto.ChangePwdDto;
import com.usermanagementservice.dto.UserDto;
import com.usermanagementservice.exception.UserAlreadyExistException;
import com.usermanagementservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto<?>> registerUser(@RequestBody UserDto userDto)
            throws UserAlreadyExistException {
        boolean isRegistered = userService.registerUser(userDto);
        if (isRegistered) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<UserDto>(true,"User registered successfully.", userDto));
        }else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseDto<UserDto>(false,"User already exists.", userDto));
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<ApiResponseDto<?>> verifyOtp(@RequestBody UserDto userDto){
        if(userService.verifyOtp(userDto.getEmail(), userDto.getVerificationCode())){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<>(true,"OTP verified successfully.", userDto.getEmail()));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto<>(false,"Invalid OTP.", userDto.getEmail()));

    }

    @PostMapping("/newPwd")
    public ResponseEntity<ApiResponseDto<?>> changePassword(@RequestBody UserDto userDto){
        if(userService.resetPassword(userDto.getEmail(), userDto.getVerificationCode(), userDto.getPassword())){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<>(true,"Password changed successfully.", userDto.getEmail()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto<>(false,"Invalid Password.", userDto.getEmail()));
    }

    @PostMapping("/changePwd")
    public ResponseEntity<ApiResponseDto<?>> changePassword(@RequestBody ChangePwdDto changePwdDto){
        String result = userService.changePassword(changePwdDto);
        if(result.equals("Success")){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<ChangePwdDto>(true,"Password changed successfully.", changePwdDto));
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<ChangePwdDto>(false,"Invalid Password.", changePwdDto));
        }
    }

}
