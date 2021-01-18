package com.security.infrastructure.provider;

import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.security.application.model.SignUpResponse;
import com.security.interfaces.web.model.RegistrationForm;

public interface AwsCognitoProvider {

	SignUpResponse createUser(RegistrationForm registrationForm);
}
