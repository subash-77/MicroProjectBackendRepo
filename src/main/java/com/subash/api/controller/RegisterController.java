package com.subash.api.controller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.subash.api.model.PsychiatristLogin;
import com.subash.api.model.Token;
import com.subash.api.model.User;
import com.subash.api.service.RegisterService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/register")
@CrossOrigin("http://localhost:3000")

public class RegisterController {
	
	
	public RegisterController(RegisterService service) {
		super();
		this.service = service;
	}


	RegisterService service;
	
    private static final String SECRET_KEY = "SUBASH";

    private static final long EXPIRATION_TIME = 3600000; 
	
	
	@PostMapping
	public String insertRegister(@RequestBody User user) {
		String msg = "";

		try {
			service.addRegister(user);
			msg += "addSuccess";

		} catch (Exception e) {
			msg += "addFailure";
		}
		return msg;

	}
	
	 @PostMapping("/checkCredentials")
	    public String checkCredentials(@RequestBody User user) {
	        boolean isValid = service.checkCredentials(user.getEmail(), user.getPassword());
	       
	        if (isValid) {


	        	String token = generateToken(user.getEmail());
	            String role = service.findRole(user.getEmail());
	            int patientId = service.findPatientId(user.getEmail());

	            PsychiatristLogin psychiatristLogin = null;
	            if ("psychiatrist".equals(role)) {
	                psychiatristLogin = service.findPsyId(user.getEmail());
	            }

	           
	            if ("psychiatrist".equals(role) && psychiatristLogin != null) {
	                return "validCredentials:" + token + ":" + role + ":" + psychiatristLogin.getPsychiatristId();
	            } else {
	                return "validCredentials:" + token + ":" + role + ":" + patientId+":"+patientId; 
	            }
	        } else {
	            return "invalidCredentials";
	        }
	    }
	 @PostMapping("/addToken")
		public String insertToken(@RequestBody Token token) {
			String msg = "";

			try {
				service.addToken(token);
				msg += "tokenAddSuccess";

			} catch (Exception e) {
				msg += "tokenaddFailure";
			}
			return msg;

		}
	 @PostMapping("/deleteToken")
		public String deleteToken(@RequestParam String randomvalue) {
			String msg = "";

			try {
				service.deleteToken(randomvalue);
				msg += "tokenDeleteSuccess";

			} catch (Exception e) {
				msg += "tokenDeleteFailure";
			}
			return msg;

		}
	 @GetMapping("/gettokendetails")
	    public String getTokenDetails(@RequestParam String randomvalue) {
		 
	        try {
	            boolean isTokenValid = service.isTokenValid(randomvalue);
	            if (isTokenValid) {
	            	
	                return "Token is valid and not expired";
	            } else {
	            	service.deleteToken(randomvalue);
	                return "Token is expired or not found";
	            }
	        } catch (Exception e) {
	            service.deleteToken(randomvalue);
	        	return "Token Deleted and Session Expired Good Bye";
	        }
	    }
	 
	    private String generateToken(String username) {
	        Instant now = Instant.now();
	        Instant expiryTime = now.plus(EXPIRATION_TIME, ChronoUnit.MILLIS);

	        return Jwts.builder()
	                .setSubject(username)
	                .setIssuedAt(Date.from(now))
	                .setExpiration(Date.from(expiryTime))
	                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
	                .compact();
	    }
}
