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
@Table(name ="psychiatristavailability")
public class PsychiatristAvailability {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int psychiatristavailableId;
	@Column(name = "availabledate")
	private String availableDate;
	@Column(name = "slot1")
	private int slot1;
	@Column(name = "slot2")
	private int slot2;
	@Column(name = "slot3")
	private int slot3;
	public PsychiatristAvailability() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PsychiatristAvailability(int psychiatristavailableId, String availableDate, int slot1, int slot2,
			int slot3) {
		super();
		this.psychiatristavailableId = psychiatristavailableId;
		this.availableDate = availableDate;
		this.slot1 = slot1;
		this.slot2 = slot2;
		this.slot3 = slot3;
	}
	public int getPsychiatristavailableId() {
		return psychiatristavailableId;
	}
	public void setPsychiatristavailableId(int psychiatristavailableId) {
		this.psychiatristavailableId = psychiatristavailableId;
	}
	public String getAvailableDate() {
		return availableDate;
	}
	public void setAvailableDate(String availableDate) {
		this.availableDate = availableDate;
	}
	public int getSlot1() {
		return slot1;
	}
	public void setSlot1(int slot1) {
		this.slot1 = slot1;
	}
	public int getSlot2() {
		return slot2;
	}
	public void setSlot2(int slot2) {
		this.slot2 = slot2;
	}
	public int getSlot3() {
		return slot3;
	}
	public void setSlot3(int slot3) {
		this.slot3 = slot3;
	}
	@Override
	public String toString() {
		return "PsychiatristAvailability [psychiatristavailableId=" + psychiatristavailableId + ", availableDate="
				+ availableDate + ", slot1=" + slot1 + ", slot2=" + slot2 + ", slot3=" + slot3 + "]";
	}
	
	
	
	
	
	
	
}
