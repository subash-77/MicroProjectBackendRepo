package com.subash.api.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@Column(name = "userName")
	private String userName;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "role")
	@Value("Admin")
	private String role;

	@OneToMany(targetEntity = Appointment.class, cascade = CascadeType.ALL)
	@JoinColumn()
	private List<Appointment> appointment = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL)
	private PsychiatristLogin psychiatristLogin;

	public User() {
		super();
	}

	public User(int userId, String userName, String email, String password, String role, List<Appointment> appointment,
			PsychiatristLogin psychiatristLogin) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.appointment = appointment;
		this.psychiatristLogin = psychiatristLogin;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Appointment> getAppointment() {
		return appointment;
	}

	public void setAppointment(List<Appointment> appointment) {
		this.appointment = appointment;
	}

	public PsychiatristLogin getPsychiatristLogin() {
		return psychiatristLogin;
	}

	public void setPsychiatristLogin(PsychiatristLogin psychiatristLogin) {
		this.psychiatristLogin = psychiatristLogin;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", role=" + role + ", appointment=" + appointment + ", psychiatristLogin=" + psychiatristLogin + "]";
	}

	

}
