package com.altimetrik.token.util;

import java.util.Date;
import java.util.UUID;

import com.altimetrik.token.model.TokenResponse;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

public class TokenGenerator {

	private String secret;
	private int tokenExpirationTime;

	public TokenGenerator(String secret, int tokenExpirationTime) {
		super();
		this.secret = secret;
		this.tokenExpirationTime = tokenExpirationTime;
	}

	public TokenResponse generateToken(String userName, String pwd) throws JOSEException {
		String jwtToken = null;
		com.nimbusds.jwt.JWTClaimsSet.Builder jwtBuilder = new JWTClaimsSet.Builder()
				.expirationTime(new Date(System.currentTimeMillis() + tokenExpirationTime))
				.jwtID(UUID.randomUUID().toString());
		jwtBuilder.claim(TokenApiConstants.USER_NAME_HEADER, userName).claim(TokenApiConstants.USER_PWD_HEADER, pwd);
		JWTClaimsSet claimsSet = jwtBuilder.build();
		SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS512), claimsSet);
		JWSSigner jwsSigner = new MACSigner(secret.getBytes());
		signedJWT.sign(jwsSigner);
		jwtToken = signedJWT.serialize();
		jwtToken = "Bearer " + jwtToken;
		return new TokenResponse(jwtToken);
	}

}
