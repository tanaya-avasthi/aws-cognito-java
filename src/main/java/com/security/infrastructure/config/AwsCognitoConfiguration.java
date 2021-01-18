package com.security.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;

@Configuration
public class AwsCognitoConfiguration {
	
	//move the configurations of AWS here
	
}
