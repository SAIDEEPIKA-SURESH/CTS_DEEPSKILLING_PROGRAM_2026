package com.example.demo;


import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class OtpMailService {

    @Autowired
    private JavaMailSender mailSender;

    public String sendOtp(String toEmail) {

        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your OTP Verification Code");
        message.setText("Your OTP is: " + otp + "\nValid for 5 minutes.");

        mailSender.send(message);

        return otp;
    }
}
