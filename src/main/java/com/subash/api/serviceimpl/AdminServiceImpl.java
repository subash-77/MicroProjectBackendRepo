package com.subash.api.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.subash.api.model.Appointment;
import com.subash.api.model.User;
import com.subash.api.ownrepo.AdminOwnRepo;
import com.subash.api.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	public AdminServiceImpl(AdminOwnRepo ownrepo) {
		super();
		this.ownrepo = ownrepo;
	}

	AdminOwnRepo ownrepo;

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
	
	public List<User> getAllPatient() {
		return ownrepo.findAllPatients();
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
	public List<Appointment> getAllAppointment() {
		
		return ownrepo.findAllAppointment();
	}

	@Override
	public String addAppointment(Appointment appointment) {
		if (appointment != null) {
			ownrepo.saveAppointment(appointment);
			return "Add Success";
		} else {
			return "Add Failure";
		}
	}

	@Override
	public String getPatientEmail(int id) {
	
		return ownrepo.getPatientEmail(id);
	}

}
