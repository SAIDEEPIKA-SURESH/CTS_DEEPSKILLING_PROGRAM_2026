package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/otp")
public class OtpController {

    @Autowired
    private OtpMailService otpMailService;

    @GetMapping("/send")
    public String sendOtp(@RequestParam String email) {
        otpMailService.sendOtp(email);
        return "OTP sent successfully to " + email;
    }
}

