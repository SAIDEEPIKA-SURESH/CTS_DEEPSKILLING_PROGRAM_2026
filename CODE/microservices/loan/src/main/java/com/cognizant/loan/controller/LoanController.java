package com.cognizant.loan.controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoanController.class);

    @GetMapping("/loans/{number}")
    public Map<String, Object> getLoanDetails(@PathVariable("number") String number) {
        LOGGER.info("START: getLoanDetails() - Number: {}", number);
        
        Map<String, Object> response = new HashMap<>();
        response.put("number", number);
        response.put("type", "car");
        response.put("loan", 400000);
        response.put("emi", 3258);
        response.put("tenure", 18);
        
        LOGGER.info("END: getLoanDetails()");
        return response;
    }
}
