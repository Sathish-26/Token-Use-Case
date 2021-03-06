package com.altimetrik.token.util;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.altimetrik.token.exception.SecurityException;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Component
public class TokenValidator {

	private static final Logger logger = LoggerFactory.getLogger(TokenValidator.class);

	@Value("${jwt.token.secret}")
	private String secret;

	public void validateJwtClaims(String jwtToken, String claimName, String claimValue)
			throws ParseException, JOSEException {
		SignedJWT signedJWT = parseToken(jwtToken);
		JWTClaimsSet claimSet = signedJWT.getJWTClaimsSet();
		String userName = claimSet.getClaim(claimName).toString();
		if (userName != null && !claimValue.equalsIgnoreCase(userName)) {
			throw new SecurityException("Token is invalid", "Username is invalid", HttpStatus.FORBIDDEN);
		}
	}

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