package com.subash.api.serviceimpl;

import java.time.Instant;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.subash.api.model.PsychiatristLogin;
import com.subash.api.model.Token;
import com.subash.api.model.User;
import com.subash.api.ownrepo.RegisterOwnRepo;
import com.subash.api.service.RegisterService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

@Service
public class RegisterServiceImpl implements RegisterService {
	private static final String SECRET_KEY = "SUBASH";

	public RegisterServiceImpl(RegisterOwnRepo ownrepo) {
		super();
		this.ownrepo = ownrepo;
	}

	RegisterOwnRepo ownrepo;

	@Override
	public void addRegister(User user) {
		ownrepo.save(user);
	}

	@Override
	public boolean checkCredentials(String email, String password) {
		User user = ownrepo.findByEmail(email);
		return user != null && user.getPassword().equals(password);
	}

	@Override
	public void addToken(Token token) {
		ownrepo.saveToken(token);
		
	}

	@Override
	 public boolean isTokenValid(String randomValue) {
           
            String tokenString = ownrepo.findByRandomValue(randomValue);
            if (tokenString != null) {
               
                Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(tokenString)
                    .getBody();
                
                Instant now = Instant.now();
                Date expiryDate = claims.getExpiration();
                
                return !now.isAfter(expiryDate.toInstant());
            }
        
        
        return false;
    }

	@Override
	public void deleteToken(String randomvalue) {
		ownrepo.deleteTokenIfExpiried(randomvalue);
			
		
	}

	@Override
	public String findRole(String email) {
		
		User user = ownrepo.findByEmail(email);
		return user.getRole();
	}

	@Override
	public PsychiatristLogin findPsyId(String email) {
		User user = ownrepo.findPsyId(email);
		return user.getPsychiatristLogin();
	}

	@Override
	public int findPatientId(String email) {
		User user = ownrepo.findPatientId(email);
		return user.getUserId();
	}

}
