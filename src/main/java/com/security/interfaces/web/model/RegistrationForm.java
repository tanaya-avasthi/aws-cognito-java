package com.security.interfaces.web.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationForm {
	
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String password;
}
