package com.zibble.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

public interface Service {

	ResponseEntity<JsonNode> otpService(RestTemplate restTemplate, String mobileNumberUD);
	
	ResponseEntity<JsonNode> verifyOtp(RestTemplate restTemplate, String otp, String sessionId);

}