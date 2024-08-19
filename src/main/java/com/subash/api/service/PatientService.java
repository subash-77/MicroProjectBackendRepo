package com.subash.api.service;

import java.util.List;

import com.subash.api.model.Appointment;
import com.subash.api.model.CarePlan;
import com.subash.api.model.User;

public interface PatientService {

	public String addJewellery(User user);

	public User getJewellery(int id);

	public List<User> getAllJewellery();
	
	public List<User> getAllAppointment(int id);
	
	public List<CarePlan> getAllCarePlan(int id);

	public void updateJewellery(User user);
	
	public void updateCarePlan(CarePlan carePlan);

	public void deleteJewellery(int id);
}
