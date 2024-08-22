package com.subash.api.ownrepo;

import java.util.List;
import com.subash.api.model.CarePlan;
import com.subash.api.model.EHR;
import com.subash.api.model.User;

public interface PatientOwnRepo {
	public void save(User user);

	public User findById(int id);

	public List<User> findAll();
	
	public List<User> findAllAppointment(int id);
	
	public List<CarePlan> findAllCarePlan(int id);

	public void update(User user);
	
	public void updateCarePlan(CarePlan careplan);

	public void deleteById(int id);
	
	public List<EHR> getEHRsByPatientId(String patientId);

}
