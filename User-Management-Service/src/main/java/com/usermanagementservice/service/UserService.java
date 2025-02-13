package com.usermanagementservice.service;

import com.usermanagementservice.dto.ApiResponseDto;
import com.usermanagementservice.dto.UserDto;
import com.usermanagementservice.exception.UserAlreadyExistException;
import org.springframework.http.ResponseEntity;

public interface UserService {
    boolean registerUser(UserDto userDto) throws UserAlreadyExistException;

}
