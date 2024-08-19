package com.subash.api.service;

import java.util.List;

import com.subash.api.model.Appointment;
import com.subash.api.model.User;

public interface AdminService {

	public String addJewellery(User user);
	
	public String addAppointment(Appointment appointment);

	public User getJewellery(int id);

	public List<User> getAllJewellery();
	
	public List<User> getAllPatient();
	
	public List<Appointment> getAllAppointment();

	public void updateJewellery(User user);

	public void deleteJewellery(int id);
	
	public String getPatientEmail(int id);
}
