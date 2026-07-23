package com.cognizant.springlearn.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        LOGGER.info("START: securityFilterChain()");
        
        http.csrf(csrf -> csrf.disable())
            .httpBasic(Customizer.withDefaults())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/authenticate").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/countries").hasRole("USER")
                .anyRequest().authenticated()
            )
            .addFilterBefore(new JwtAuthorizationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class);

        LOGGER.info("END: securityFilterChain()");
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        LOGGER.info("START: userDetailsService()");
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("pwd"))
                .roles("ADMIN")
                .build();
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder.encode("pwd"))
                .roles("USER")
                .build();
        LOGGER.info("END: userDetailsService()");
        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        LOGGER.info("Start passwordEncoder()");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
