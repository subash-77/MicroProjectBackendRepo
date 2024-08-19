package com.subash.api.ownrepo;

import com.subash.api.model.Token;
import com.subash.api.model.User;

public interface RegisterOwnRepo {

	public void save(User user);

	public void saveToken(Token token);

	public User findByEmail(String email);

	String findByRandomValue(String randomValue);
	
	public void deleteTokenIfExpiried(String randomvalue);
	
	public User findRole(String email);
	
	public User findPatientId(String email);
	
	public User findPsyId(String email);

}
