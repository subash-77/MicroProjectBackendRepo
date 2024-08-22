package com.subash.api.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.subash.api.model.CarePlan;
import com.subash.api.model.EHR;
import com.subash.api.model.User;
import com.subash.api.ownrepo.PatientOwnRepo;
import com.subash.api.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	public PatientServiceImpl(PatientOwnRepo ownrepo) {
		super();
		this.ownrepo = ownrepo;
	}

	PatientOwnRepo ownrepo;

	@Override
	public String addJewellery(User user) {
		if (user != null) {
			ownrepo.save(user);
			return "Add Success";
		} else {
			return "Add Failure";
		}

	}

	@Override
	public User getJewellery(int id) {
		return ownrepo.findById(id);
	}

	public List<User> getAllJewellery() {
		return ownrepo.findAll();
	}

	@Override
	public void updateJewellery(User user) {
		ownrepo.update(user);
	}

	@Override
	public void deleteJewellery(int id) {
		ownrepo.deleteById(id);
	}

	@Override
	public List<User> getAllAppointment(int id) {

		return ownrepo.findAllAppointment(id);
	}

	@Override
	public List<CarePlan> getAllCarePlan(int id) {
		return ownrepo.findAllCarePlan(id);
	}

	@Override
	public void updateCarePlan(CarePlan carePlan) {

		ownrepo.updateCarePlan(carePlan);

	}
	
	public List<EHR> getEHRsByPatientId(String patientId) {
        return ownrepo.getEHRsByPatientId(patientId);
    }

}
