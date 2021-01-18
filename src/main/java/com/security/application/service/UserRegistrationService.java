package com.security.application.service;

import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.security.application.model.SignUpResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.security.infrastructure.provider.AwsCognitoProvider;
import com.security.interfaces.web.model.RegistrationForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class UserRegistrationService {
	
	private final AwsCognitoProvider awsCognitoProvider;
	
	public SignUpResponse register(RegistrationForm registrationForm) {
		
		log.info("UserRegistrationService.register"+registrationForm.getUsername());
		return awsCognitoProvider.createUser(registrationForm);
		
	}
}
