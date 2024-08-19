package com.subash.api.ownrepo;

import java.util.List;

import com.subash.api.model.Appointment;
import com.subash.api.model.User;

public interface AdminOwnRepo {
	public void save(User user);
	
	public void saveAppointment(Appointment appointment);

	public User findById(int id);

	public List<User> findAll();
	
	public List<User> findAllPatients();
	
	public List<Appointment> findAllAppointment();

	public void update(User user);

	public void deleteById(int id);
	
	public String getPatientEmail(int id);

}
