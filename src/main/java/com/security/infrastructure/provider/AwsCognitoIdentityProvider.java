package com.security.infrastructure.provider;

import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.services.cognitoidp.model.*;
import com.security.application.model.SignUpResponse;
import org.springframework.beans.factory.annotation.Value;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.security.interfaces.web.model.RegistrationForm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AwsCognitoIdentityProvider implements AwsCognitoProvider {
	
	@Value( "${aws.cognito.clientId}" )
	private String clientId;

	@Value( "${aws.region}" )
	private String awsRegion;
	
	
	@Override
	public SignUpResponse createUser(RegistrationForm registrationForm) {
		
		log.info("AwsCognitoIdentityProvider.createUser");
		
		return createUserInAwsCognito(registrationForm);
	}
	
	public AWSCognitoIdentityProvider getAmazonCognitoIdentityClient() {
		//anonymous credentials - unauthenticated request
		AnonymousAWSCredentials anonymousAWSCredentials = new AnonymousAWSCredentials();
		return AWSCognitoIdentityProviderClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(anonymousAWSCredentials))
				.withRegion(awsRegion)
				.build();
    }

	/**
	 * Create a user in the user pool
	 * @param registrationForm signup details entered by the user
	 * @return whether the call was successful or not.
	 */
	private SignUpResponse createUserInAwsCognito(RegistrationForm registrationForm) {
		log.info("AwsCognitoIdentityProvider.createUserInAwsCognito");
		try {
			//get the cognito identity client
			AWSCognitoIdentityProvider client = getAmazonCognitoIdentityClient();

			//generate the signup request with no client secret
			SignUpRequest signUpRequest = new SignUpRequest()
					.withClientId(clientId)
					.withUsername(registrationForm.getUsername())
					.withPassword(registrationForm.getPassword())
					.withUserAttributes(
							new AttributeType()
									.withName("email")
									.withValue(registrationForm.getEmail())
					);
				client.signUp(signUpRequest);
                return new SignUpResponse("User signup was successful");
        } catch (NotAuthorizedException exception) {
            throw new NotAuthorizedException("Incorrect username or password entered");
        } catch (UsernameExistsException usernameExistsException) {
			throw new UsernameExistsException("User already exists");
		}
    }
	
}
