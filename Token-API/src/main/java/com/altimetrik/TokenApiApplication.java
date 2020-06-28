package com.altimetrik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.altimetrik")
public class TokenApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokenApiApplication.class, args);
	}

}
