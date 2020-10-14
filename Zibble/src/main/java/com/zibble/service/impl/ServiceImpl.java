package com.zibble.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.zibble.ZibbleApp;
import com.zibble.service.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


import org.springframework.web.client.RestTemplate;

@Component
public class ServiceImpl implements Service
{
	private static final Logger log = LoggerFactory.getLogger(ZibbleApp.class);
	
	@Value("${otp.base.url}")
	private String url;
	
	@Value("${otp.service.key}")
	private String secretKey;	
	
	
	@Override
	public ResponseEntity<JsonNode> otpService(RestTemplate restTemplate, String mobileNumberUD) {
		
		String finalUrl = String.format("%s/%s/SMS/%s/AUTOGEN", url, secretKey, mobileNumberUD);
		ResponseEntity<JsonNode> response = null;
		
			try {
			
				log.info("final  {}", finalUrl);
				response = restTemplate.postForEntity(finalUrl, null, JsonNode.class);
				log.info("response body {}", response.getBody().toString());
				JsonNode node = response.getBody();
			
				log.info("node  {}, {}", node.get("Status"), node.get("Details"));
			
			
				} catch (Exception e) { 
					log.error("error occured {}", e);

					}
		return response;
		}


	@Override
	public ResponseEntity<JsonNode> verifyOtp(RestTemplate restTemplate, String otp, String sessionId) {
		String finalUrl = String.format("%s/%s/SMS/VERIFY/%s/%s", url, secretKey, sessionId, otp);
		ResponseEntity<JsonNode> response = null;
		
			try {
			
				log.info("final  {}", finalUrl);
				response = restTemplate.postForEntity(finalUrl, null, JsonNode.class);
				log.info("response body {}", response.getBody().toString());
				JsonNode node = response.getBody();
			
				log.info("node  {}, {}", node.get("Status"), node.get("Details"));
			
			
				} catch (Exception e) { 
					log.error("error occured {}", e);

					}
		return response;

	}
   
	
    
}