package com.dolph.DolphBank.configuration;

import com.dolph.DolphBank.filters.JwtFilter;
import com.dolph.DolphBank.services.PersonServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfiguration {

    private final PersonServiceImpl personService;
    private final JwtFilter jwtFilter;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder bCryptPasswordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(personService).passwordEncoder(bCryptPasswordEncoder);

        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeRequests((auth) -> auth.
                        requestMatchers("/person/login").permitAll()
                                .requestMatchers("/person/register").permitAll()
                                .requestMatchers("/person/forgot-password").permitAll()
                                .requestMatchers("/person/activate-password/**").permitAll()
                                //.requestMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                );

        /*
                .requestMatchers("/person/login").permitAll()
                .requestMatchers("/person/register").permitAll()
                .requestMatchers("/person/forgot-password").permitAll()
                .requestMatchers("/person/activate-password/**").permitAll()
                .anyRequest().authenticated()

         */

                http.addFilterBefore(this.jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
