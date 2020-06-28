package com.altimetrik.token.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.token.model.TokenResponse;
import com.altimetrik.token.util.TokenAppConstants;
import com.altimetrik.token.util.TokenValidator;
import com.nimbusds.jose.JOSEException;

@RestController
public class TokenTestController {

	@Autowired
	private TokenValidator tokenValidator;

	@RequestMapping(value = "/test/{userName}", method = RequestMethod.GET, produces = "application/json")
	public TokenResponse testToken(@PathVariable("userName") String userName,
			@RequestHeader(name = "Authorization", required = true) String authToken)
			throws ParseException, JOSEException {
		String jwtToken = authToken.substring("Bearer".length()).trim();
		tokenValidator.validateJwtClaims(jwtToken, TokenAppConstants.USER_NAME_HEADER, userName);
		return new TokenResponse(TokenAppConstants.SUCCESS_MESSAGE);
	}
}
