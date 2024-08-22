package com.subash.api.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "psychiatristlogin")
public class PsychiatristLogin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int psychiatristId;
	@Column(name = "username")
	private String username;
	@Column (name ="email")
	private String email;
	@Column (name ="password")
	private String password;
	@Column (name ="specialization")
	private String specialization;
	@Column (name ="experienceYear")
	private String experienceYear;
	@Column (name ="bio")
	private String bio;
	@Column (name ="education")
	private String education;
	@Column (name ="fees")
	private String fees;
	
	@OneToOne(cascade = CascadeType.ALL)
	private PsychiatristAvailability psychiatristAvailability;
	
	public PsychiatristLogin() {
		super();
	}

	public PsychiatristLogin(int psychiatristId, String username, String email, String password, String specialization,
			String experienceYear, String bio, String education, String fees,
			PsychiatristAvailability psychiatristAvailability) {
		super();
		this.psychiatristId = psychiatristId;
		this.username = username;
		this.email = email;
		this.password = password;
		this.specialization = specialization;
		this.experienceYear = experienceYear;
		this.bio = bio;
		this.education = education;
		this.fees = fees;
		this.psychiatristAvailability = psychiatristAvailability;
	}

	public int getPsychiatristId() {
		return psychiatristId;
	}

	public void setPsychiatristId(int psychiatristId) {
		this.psychiatristId = psychiatristId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getExperienceYear() {
		return experienceYear;
	}

	public void setExperienceYear(String experienceYear) {
		this.experienceYear = experienceYear;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getFees() {
		return fees;
	}

	public void setFees(String fees) {
		this.fees = fees;
	}

	public PsychiatristAvailability getPsychiatristAvailability() {
		return psychiatristAvailability;
	}

	public void setPsychiatristAvailability(PsychiatristAvailability psychiatristAvailability) {
		this.psychiatristAvailability = psychiatristAvailability;
	}

	@Override
	public String toString() {
		return "PsychiatristLogin [psychiatristId=" + psychiatristId + ", username=" + username + ", email=" + email
				+ ", password=" + password + ", specialization=" + specialization + ", experienceYear=" + experienceYear
				+ ", bio=" + bio + ", education=" + education + ", fees=" + fees + ", psychiatristAvailability="
				+ psychiatristAvailability + "]";
	}
	
	
	
}
