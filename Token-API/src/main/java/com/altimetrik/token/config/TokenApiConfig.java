package com.altimetrik.token.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.altimetrik.token.util.TokenGenerator;

@Configuration
public class TokenApiConfig {

	@Value("${jwt.token.expirationTime}")
	private int expirationTime;

	@Value("${jwt.token.secret}")
	private String secretkey;

	@Bean
	public TokenGenerator tokenGenerator() {
		return new TokenGenerator(secretkey, expirationTime);
	}

}
