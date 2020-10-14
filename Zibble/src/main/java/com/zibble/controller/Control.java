package com.zibble.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.zibble.entity.UserDetailsUD;
import com.zibble.service.Service;
import com.zibble.service.UserService;


@RestController
@RequestMapping("/login")
public class Control {
	
	@Autowired
    private Service service;
	
	@Autowired
	private UserService userService;


	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@PostMapping("generateOtp")
    public Map<String, String> getSessionId(@RequestParam("number") String number)
    {
		ResponseEntity<JsonNode> otpResponse= this.service.otpService(restTemplate(new RestTemplateBuilder()), number);
		JsonNode node = otpResponse.getBody();
		//String sessionId = StringUtils.chop(node.get("Details").toString().substring(1));
		String sessionId = node.get("Details").toString().replace("\"", "");
		
		//String arpan = "My name is \"Arpan\"";  // to print "My name is "Arpan"
		
        Map<String, String> response = new HashMap<>();
        response.put("sessionId", sessionId );

        return response;
    }	
	
	@PostMapping("verifyOtp")
    public Map<String, String> verifyOtp(@RequestParam("number") String mobileNumberUD, @RequestParam("otp") String otp, @RequestParam("sessionId") String sessionId)
    {
		ResponseEntity<JsonNode> otpVerify= this.service.verifyOtp(restTemplate(new RestTemplateBuilder()), otp, sessionId);
		JsonNode node = otpVerify.getBody();
		String otpStatus = node.get("Details").toString().replace("\"", "");
		
		String entry = "Error: Query didn't run";
		if(otpStatus == "OTP Matched") {
			
			// check the database if user already exists
			UserDetailsUD existing = userService.findByMobileNumberUD(mobileNumberUD);
	        if (existing != null){
	        	entry = "Existing User, no entry made in database";
				}	
	        	
	        else {
	        	userService.saveUser(mobileNumberUD);
	        	entry = "New User, entry made in database";
	        }
	        	
		}
        Map<String, String> response = new HashMap<>();
        response.put("entry", mobileNumberUD + " is " + entry);

        return response;
    }	

	
}
