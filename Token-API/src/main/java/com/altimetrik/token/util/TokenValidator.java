package com.altimetrik.token.util;

import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;

@Component
public class TokenValidator {

	private static final Logger logger = LoggerFactory.getLogger(TokenValidator.class);

	@Value("${jwt.token.secret}")
	private String secret;

	public SignedJWT parseToken(String token) throws ParseException, JOSEException {
		SignedJWT signedJWT = SignedJWT.parse(token);
		JWSVerifier jwsVerifier = new MACVerifier(secret.getBytes());
		if (!signedJWT.verify(jwsVerifier)) {
			throw new JOSEException("JWT Token can't be verified");
		}
		if (signedJWT.getJWTClaimsSet() != null && isTokenExpired(signedJWT.getJWTClaimsSet().getExpirationTime())) {
			throw new JOSEException("JWT Token is expired");
		}
		return signedJWT;
	}

	private boolean isTokenExpired(Date tokenExpDate) {
		boolean isTokenExpired = true;
		if (tokenExpDate != null) {
			logger.info("token expiring date: {}", tokenExpDate);
			Date currentDate = new Date(System.currentTimeMillis());
			logger.info("current date: {}", currentDate);

			if (tokenExpDate.getTime() > currentDate.getTime()) {
				logger.info("Token is Valid");
				isTokenExpired = false;
			}
		}
		return isTokenExpired;
	}
}