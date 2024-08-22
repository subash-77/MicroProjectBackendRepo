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
@Table(name = "appointment")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int appointmentId;
	@Column(name = "psychiatristId")
	private String psychiatristId;
	@Column(name = "appointmentDate")
	private String appointmentDate;
	@Column(name = "appointmentSlot")
	private String appointmentSlot;
	@Column(name = "status")
	private String status;

	@OneToOne(cascade = CascadeType.ALL)
	private PatientInfo patientInfo;

	@OneToOne(cascade = CascadeType.ALL)
	private Payment payment;

	public Appointment() {
		super();
	}

	public Appointment(int appointmentId, String psychiatristId, String appointmentDate, String appointmentSlot,
			String status, PatientInfo patientInfo, Payment payment) {
		super();
		this.appointmentId = appointmentId;
		this.psychiatristId = psychiatristId;
		this.appointmentDate = appointmentDate;
		this.appointmentSlot = appointmentSlot;
		this.status = status;
		this.patientInfo = patientInfo;
		this.payment = payment;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getPsychiatristId() {
		return psychiatristId;
	}

	public void setPsychiatristId(String psychiatristId) {
		this.psychiatristId = psychiatristId;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public PatientInfo getPatientInfo() {
		return patientInfo;
	}

	public void setPatientInfo(PatientInfo patientInfo) {
		this.patientInfo = patientInfo;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public String getAppointmentSlot() {
		return appointmentSlot;
	}

	public void setAppointmentSlot(String appointmentSlot) {
		this.appointmentSlot = appointmentSlot;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", psychiatristId=" + psychiatristId
				+ ", appointmentDate=" + appointmentDate + ", appointmentSlot=" + appointmentSlot + ", status=" + status
				+ ", patientInfo=" + patientInfo + ", payment=" + payment + "]";
	}

	

}
