package com.subash.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.subash.api.model.CarePlan;
import com.subash.api.model.EHR;
import com.subash.api.model.User;
import com.subash.api.serviceimpl.PatientServiceImpl;

@RestController
@RequestMapping("/patient")
@CrossOrigin("http://localhost:3000")

public class PatientController {

	PatientServiceImpl service;

	public PatientController(PatientServiceImpl service) {
		super();
		this.service = service;
	}

	@PostMapping
	public String insertJewellery(@RequestBody User user) {
		String msg = "";

		try {
			service.addJewellery(user);
			msg += "addSuccess";

		} catch (Exception e) {
			msg += "addFailure";
		}
		return msg;

	}

	@PutMapping
	public String updateJewellery(@RequestBody User user) {
		String msg = "";

		try {

			service.updateJewellery(user);
			msg += "updateSuccess";

		} catch (Exception e) {
			msg += "updateFailure";
		}
		return msg;
	}
	
	@PutMapping("/updateCarePlans")
	public String updateCarePlan(@RequestBody CarePlan carePlan) {
		String msg = "";

		try {

			service.updateCarePlan(carePlan);
			msg += "updateSuccess";

		} catch (Exception e) {
			msg += "updateFailure";
		}
		return msg;
	}

	@GetMapping("{id}")
	public User getJewelleryById(@PathVariable("id") int id) {
		return service.getJewellery(id);
	}

	@GetMapping("/all")
	public List<User> getJewellery() {
		return service.getAllJewellery();
	}
	@GetMapping("/IsappointmentScheduledOrNot/{id}")
	public List<User> getAppointmentsById(@PathVariable("id") int id) {
		return service.getAllAppointment(id);
	}
	
	@GetMapping("/getAllCarePlan/{id}")
	public List<CarePlan> getAllCarePlanById(@PathVariable("id") int id) {
		return service.getAllCarePlan(id);
	}
	
	

	@DeleteMapping("{id}")
	public String deleteFirById(@PathVariable("id") int id) {
		String msg = "";

		try {

			service.deleteJewellery(id);
			msg += "deleteSuccess";

		} catch (Exception e) {
			msg += "deleteFailure";
		}
		return msg;

	}
	
	@GetMapping("/ehr")
    public ResponseEntity<List<EHR>> getEHRsByPatientId(@RequestParam("patientId") String patientId) {
        List<EHR> ehrRecords = service.getEHRsByPatientId(patientId);
        if (ehrRecords.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ehrRecords);
    }
}
