package com.subash.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "patientinfo")
public class PatientInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int patientinfoId;
	@Column(name = "name")
	private String name;
	@Column(name = "phoneno")
	private String phoneNo;
	@Column(name = "gender")
	private String gender;
	@Column(name = "age")
	private String age;
	public PatientInfo() {
		super();
	}
	public PatientInfo(int patientinfoId, String name, String phoneNo, String gender, String age) {
		super();
		this.patientinfoId = patientinfoId;
		this.name = name;
		this.phoneNo = phoneNo;
		this.gender = gender;
		this.age = age;
	}
	public int getPatientinfoId() {
		return patientinfoId;
	}
	public void setPatientinfoId(int patientinfoId) {
		this.patientinfoId = patientinfoId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "PatientInfo [patientinfoId=" + patientinfoId + ", name=" + name + ", phoneNo=" + phoneNo + ", gender="
				+ gender + ", age=" + age + "]";
	}
	
	
}
