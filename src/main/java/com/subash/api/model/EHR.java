package com.subash.api.model;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "ehr")
public class EHR {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int recordId;
	@Column(name = "patientId")
	private String patientId;
	@Column(name = "psychiatristId")
	private String psychiatristId;
	@Column(name = "recordDate")
	private String recordsDate;
	@Lob
    @Column(name = "fileData",length=1000000000)
    private byte[] fileData;
	@Column(name = "description")
	private String description;
	public EHR() {
		super();
	}
	public EHR(int recordId, String patientId, String psychiatristId, String recordsDate, byte[] fileData,
			String description) {
		super();
		this.recordId = recordId;
		this.patientId = patientId;
		this.psychiatristId = psychiatristId;
		this.recordsDate = recordsDate;
		this.fileData = fileData;
		this.description = description;
	}
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPsychiatristId() {
		return psychiatristId;
	}
	public void setPsychiatristId(String psychiatristId) {
		this.psychiatristId = psychiatristId;
	}
	public String getRecordsDate() {
		return recordsDate;
	}
	public void setRecordsDate(String recordsDate) {
		this.recordsDate = recordsDate;
	}
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "EHR [recordId=" + recordId + ", patientId=" + patientId + ", psychiatristId=" + psychiatristId
				+ ", recordsDate=" + recordsDate + ", fileData=" + Arrays.toString(fileData) + ", description="
				+ description + "]";
	}
	
	
	
	
}
