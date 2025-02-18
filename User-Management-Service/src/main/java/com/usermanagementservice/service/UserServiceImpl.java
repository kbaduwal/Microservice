package com.usermanagementservice.service;

import com.usermanagementservice.dto.ChangePwdDto;
import com.usermanagementservice.dto.UserDto;
import com.usermanagementservice.entity.User;
import com.usermanagementservice.exception.UserAlreadyExistException;
import com.usermanagementservice.repo.UserRepository;
import com.usermanagementservice.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailUtil emailUtil;

    private static final int OTP_VALID_DURATION = 35;

    @Override
    public boolean registerUser(UserDto userDto)
            throws UserAlreadyExistException {

        boolean userByName = findUserByName(userDto.getUserName()).isPresent();
        boolean userByEmail = findUserByEmail(userDto.getEmail()).isPresent();
        boolean userByPhoneNumber = findUserByPhoneNumber(userDto.getPhoneNumber()).isPresent();

        if (userByName || userByEmail || userByPhoneNumber) {
            return false;
        }

        // Generate OTP once and reuse it.
        String otp = generateOtp();
        LocalDateTime otpExpiration = generateOtpTime();

        User user = createUser(userDto,otp,otpExpiration);
        userRepository.save(user);

        //TODO: Send email to user to unlock the account
        String to = userDto.getEmail();
        String subject = "Reset Password OTP.";
        String body = "Your OTP for password reset is: " + user.getVerificationCode() + "\nValid for " + OTP_VALID_DURATION + " minutes.";
        emailUtil.sendEmail(to, subject, body);

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

    private User createUser(UserDto userDto, String otp, LocalDateTime otpExpiration) {
        return User.builder()
                .userName(userDto.getUserName())
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .password(generateRandomPassword())
                .isVerified(false)
                .verificationCode(otp)
                .tokenExpiration(otpExpiration)
                .build();
    }


    private String generateRandomPassword() {
        return "123456";
    }

    private String generateOtp(){
        return String.format("%06d", new Random().nextInt(999999));
    }

    private LocalDateTime generateOtpTime() {
        return LocalDateTime.now().plusMinutes(OTP_VALID_DURATION);
    }

    @Override
    public boolean verifyOtp(String email, String otp) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        if(byEmail.isPresent()) {
            User user = byEmail.get();
            if(user.getVerificationCode().trim().equals(otp)
                    && user.getTokenExpiration().isAfter(LocalDateTime.now())) {
                return true;
            }

        }
        return false;

    }

    @Override
    public boolean resetPassword(String email, String otp, String newPassword) {

        if (!verifyOtp(email, otp)) {
            return false;
        }

        Optional<User> userEmail = userRepository.findByEmail(email);
        if(userEmail.isPresent()) {
            User user = userEmail.get();
            user.setPassword(newPassword);
            user.setVerified(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public String changePassword(ChangePwdDto changePwdDto) {
        Optional<User> userEmail = userRepository.findByEmail(changePwdDto.getEmail());
        if(userEmail.isPresent()) {
            User user = userEmail.get();

            if (user.getPassword().equals(changePwdDto.getOldPassword())) {
                user.setPassword(changePwdDto.getNewPassword());
                userRepository.save(user);
                return "Success";
            }

        }

        return "Invalid old password";
    }


}
