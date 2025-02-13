package com.usermanagementservice.controller;

import com.usermanagementservice.dto.ApiResponseDto;
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

}
