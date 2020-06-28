package com.altimetrik.token.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.token.model.TokenResponse;
import com.altimetrik.token.util.TokenApiConstants;
import com.altimetrik.token.util.TokenGenerator;
import com.altimetrik.token.util.TokenValidator;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@RestController
@RequestMapping("/${api.version}")
public class TokenApiController {

	@Autowired
	private TokenGenerator tokenGenerator;

	@Autowired
	private TokenValidator tokenValidator;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public TokenResponse generateToken(
			@RequestHeader(name = TokenApiConstants.USER_NAME_HEADER, required = true) String userName,
			@RequestHeader(name = TokenApiConstants.USER_PWD_HEADER, required = true) String pwd) throws Exception {
		return tokenGenerator.generateToken(userName, pwd);
	}

	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	public TokenResponse refreshToken(@RequestHeader(name = "Authorization", required = true) String authToken)
			throws JOSEException, ParseException {
		String jwtToken = authToken.substring("Bearer".length()).trim();
		SignedJWT jwt = tokenValidator.parseToken(jwtToken);
		JWTClaimsSet claims = jwt.getJWTClaimsSet();
		return tokenGenerator.generateToken(claims.getClaim(TokenApiConstants.USER_NAME_HEADER).toString(),
				claims.getClaim(TokenApiConstants.USER_PWD_HEADER).toString());
	}
}
