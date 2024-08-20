package com.subash.api.ownrepo;

import java.util.List;

import com.subash.api.model.Appointment;
import com.subash.api.model.CarePlan;
import com.subash.api.model.EHR;
import com.subash.api.model.PsychiatristLogin;

public interface PsychiatristOwnRepo {
	public void save(PsychiatristLogin psychiatristLogin);
	
	public void save(CarePlan carePlan);

	public PsychiatristLogin findById(int id);

	public List<PsychiatristLogin> findAll();
	
	public List<Appointment> findAllAppointment(int id);

	public void update(PsychiatristLogin psychiatristLogin);

	public void deleteById(int id);
	
	public EHR saveEhr(EHR ehr);

}
