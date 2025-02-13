package com.usermanagementservice.service;

import com.usermanagementservice.dto.ApiResponseDto;
import com.usermanagementservice.dto.UserDto;
import com.usermanagementservice.entity.User;
import com.usermanagementservice.exception.UserAlreadyExistException;
import com.usermanagementservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean registerUser(UserDto userDto)
            throws UserAlreadyExistException {

        boolean userByName = findUserByName(userDto.getUserName()).isPresent();
        boolean userByEmail = findUserByEmail(userDto.getEmail()).isPresent();
        boolean userByPhoneNumber = findUserByPhoneNumber(userDto.getPhoneNumber()).isPresent();

        if (userByName || userByEmail || userByPhoneNumber) {
            return false;
        }

        User user = createUser(userDto);
        userRepository.save(user);

        return true;
    }

    private Optional<User> findUserByName(String userName) throws UserAlreadyExistException {
        return userRepository.findByUserName(userName);
    }

    private Optional<User> findUserByEmail(String email) throws UserAlreadyExistException {
        return userRepository.findByEmail(email);
    }

    private Optional<User> findUserByPhoneNumber(String phoneNumber) throws UserAlreadyExistException {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    private User createUser(UserDto userDto) {
        return User.builder()
                .userName(userDto.getUserName())
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .password(generateRandomPassword())
                .isVerified(isAccountValid())
                .verificationCode(generateOtp())
                .tokenExpiration(generateOtpTime())
                .build();
    }

    private String generateRandomPassword() {
        return "Please change your password";
    }

    private boolean isAccountValid() {
        return true;
    }

    private String generateOtp(){
        return "OTP " + (int)(Math.random()*1000000);
    }

    private LocalDateTime generateOtpTime() {
        return LocalDateTime.now();
    }


}
