package com.dolph.DolphBank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class DolphBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(DolphBankApplication.class, args);
	}

}
