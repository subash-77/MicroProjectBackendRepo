package com.subash.api.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.subash.api.model.Appointment;
import com.subash.api.model.CarePlan;
import com.subash.api.model.PsychiatristLogin;
import com.subash.api.ownrepo.PsychiatristOwnRepo;
import com.subash.api.service.PsychiatristService;

@Service
public class PsychiatristServiceImpl implements PsychiatristService {

	public PsychiatristServiceImpl(PsychiatristOwnRepo ownrepo) {
		super();
		this.ownrepo = ownrepo;
	}

	PsychiatristOwnRepo ownrepo;

	@Override
	public String addJewellery(PsychiatristLogin psychiatristLogin) {
		if (psychiatristLogin != null) {
			ownrepo.save(psychiatristLogin);
			return "Add Success";
		} else {
			return "Add Failure";
		}

	}

	@Override
	public PsychiatristLogin getJewellery(int id) {
		return ownrepo.findById(id);
	}

	public List<PsychiatristLogin> getAllJewellery() {
		return ownrepo.findAll();
	}

	@Override
	public void updateJewellery(PsychiatristLogin psychiatristLogin) {
		ownrepo.update(psychiatristLogin);
	}

	@Override
	public void deleteJewellery(int id) {
		ownrepo.deleteById(id);
	}

	@Override
	public List<Appointment> getAllAppointment(int id) {
		
		return ownrepo.findAllAppointment(id);
	}

	@Override
	public String addCarePlan(CarePlan carePlan) {
		if (carePlan != null) {
			ownrepo.save(carePlan);
			return "Add Success";
		} else {
			return "Add Failure";
		}
	}

}
