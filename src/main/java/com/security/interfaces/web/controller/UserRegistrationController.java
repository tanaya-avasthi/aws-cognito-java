package com.security.interfaces.web.controller;

import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.security.application.model.SignUpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.application.service.UserRegistrationService;
import com.security.interfaces.web.model.RegistrationForm;

import static com.security.infrastructure.common.Constants.*;

@RestController
@RequestMapping("api/user-security")
public class UserRegistrationController {
	
	private final UserRegistrationService userRegistrationService;
	
	public UserRegistrationController(UserRegistrationService userRegistrationService) {
		this.userRegistrationService = userRegistrationService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<SignUpResponse> registerUser(@RequestBody RegistrationForm registrationForm) {
		return ResponseEntity.ok(this.userRegistrationService.register(registrationForm));
	}

}
