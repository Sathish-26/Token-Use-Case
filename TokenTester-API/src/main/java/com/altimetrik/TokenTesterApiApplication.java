package com.altimetrik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.altimetrik")
public class TokenTesterApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokenTesterApiApplication.class, args);
	}

}
