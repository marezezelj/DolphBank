package com.dolph.DolphBank.configuration;

import com.dolph.DolphBank.services.PersonServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalAuthentication
public class SpringSecurityConfiguration {

    private final PersonServiceImpl personService;
    //private final JwtFilter jwtFilter;

}
