package com.subash.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "careplan")
public class CarePlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int careplanId;
	@Column(name = "psychiatristId")
	private String psychiatristId;
	@Column(name = "patientId")
	private String patientId;
	@Column(name = "carecardId")
	private String carecardId;
	@Column(name = "status")
	private String status;
	public CarePlan() {
		super();
	}
	public CarePlan(int careplanId, String psychiatristId, String patientId, String carecardId, String status) {
		super();
		this.careplanId = careplanId;
		this.psychiatristId = psychiatristId;
		this.patientId = patientId;
		this.carecardId = carecardId;
		this.status = status;
	}
	public int getCareplanId() {
		return careplanId;
	}
	public void setCareplanId(int careplanId) {
		this.careplanId = careplanId;
	}
	public String getPsychiatristId() {
		return psychiatristId;
	}
	public void setPsychiatristId(String psychiatristId) {
		this.psychiatristId = psychiatristId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getCarecardId() {
		return carecardId;
	}
	public void setCarecardId(String carecardId) {
		this.carecardId = carecardId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "CarePlan [careplanId=" + careplanId + ", psychiatristId=" + psychiatristId + ", patientId=" + patientId
				+ ", carecardId=" + carecardId + ", status=" + status + "]";
	}
	
	
	
}
