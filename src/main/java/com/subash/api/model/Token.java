package com.subash.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "token")
public class Token {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "randomvalue")
	private String randomValue;
	@Column(name = "token")
	private String token;
	public Token() {
		super();
	}
	
	public Token(int id, String email, String password, String randomValue, String token) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.randomValue = randomValue;
		this.token = token;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getRandomValue() {
		return randomValue;
	}

	public void setRandomValue(String randomValue) {
		this.randomValue = randomValue;
	}

	@Override
	public String toString() {
		return "Token [id=" + id + ", email=" + email + ", password=" + password + ", randomValue=" + randomValue
				+ ", token=" + token + "]";
	}

	
	
	
}
