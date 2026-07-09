package com.cognizant.springlearn.controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @GetMapping("/authenticate")
    public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {
        LOGGER.info("START: authenticate()");
        LOGGER.debug("Authorization Header: {}", authHeader);

        String user = getUser(authHeader);
        LOGGER.debug("Decoded User: {}", user);

        String token = generateJwt(user);
        LOGGER.debug("Generated JWT: {}", token);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        LOGGER.info("END: authenticate()");
        return response;
    }

    private String getUser(String authHeader) {
        LOGGER.info("START: getUser()");
        LOGGER.debug("Header: {}", authHeader);

        // Extract credentials block after "Basic "
        String encodedCredentials = authHeader.substring("Basic ".length()).trim();
        byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
        String credentials = new String(decodedBytes);

        // credentials is of form "user:pwd"
        int colonIndex = credentials.indexOf(":");
        String user = credentials.substring(0, colonIndex);

        LOGGER.debug("Extracted User: {}", user);
        LOGGER.info("END: getUser()");
        return user;
    }

    private String generateJwt(String user) {
        LOGGER.info("START: generateJwt()");
        LOGGER.debug("User: {}", user);

        JwtBuilder builder = Jwts.builder();
        builder.setSubject(user);

        // Set the token issue time as current time
        builder.setIssuedAt(new Date());

        // Set the token expiry as 20 minutes from now (20 * 60 * 1000 = 1200000 ms)
        builder.setExpiration(new Date((new Date()).getTime() + 1200000));
        builder.signWith(SignatureAlgorithm.HS256, "secretkey");

        String token = builder.compact();

        LOGGER.debug("Token Compacted: {}", token);
        LOGGER.info("END: generateJwt()");
        return token;
    }
}
