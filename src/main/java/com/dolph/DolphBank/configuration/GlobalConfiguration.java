package com.dolph.DolphBank.configuration;

import com.dolph.DolphBank.dto.AccountRequestDTO;
import com.dolph.DolphBank.entites.Account;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.GeneralSecurityException;
import java.util.Properties;

@Configuration
@EnableScheduling
public class GlobalConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JavaMailSender mailSender() throws GeneralSecurityException {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("dolphsocial@gmail.com");
        mailSender.setPassword("hmcqcsfrnduchbuo");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.typeMap(Account.class, AccountRequestDTO.class).addMappings(mapper -> {
            mapper.map(Account::getOwner, AccountRequestDTO::setClient);
        });
        return modelMapper;    }
}
