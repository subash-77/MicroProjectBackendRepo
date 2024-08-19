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
import org.springframework.web.bind.annotation.RestController;

import com.subash.api.model.Appointment;
import com.subash.api.model.CarePlan;
import com.subash.api.model.PsychiatristLogin;
import com.subash.api.serviceimpl.PsychiatristServiceImpl;

@RestController
@RequestMapping("/psychiatrist")
@CrossOrigin("http://localhost:3000")

public class PsychiatristController {

	PsychiatristServiceImpl service;

	public PsychiatristController(PsychiatristServiceImpl service) {
		super();
		this.service = service;
	}

	@PostMapping
	public String insertJewellery(@RequestBody PsychiatristLogin psychiatristLogin) {
		String msg = "";

		try {
			service.addJewellery(psychiatristLogin);
			msg += "addSuccess";

		} catch (Exception e) {
			msg += "addFailure";
		}
		return msg;

	}
	@PostMapping("/addCarePlan")
	public String insertcareplan(@RequestBody CarePlan carePlan) {
		String msg = "";

		try {
			service.addCarePlan(carePlan);
			msg += "addSuccess";

		} catch (Exception e) {
			msg += "addFailure";
		}
		return msg;

	}

	@PutMapping
	public String updateJewellery(@RequestBody PsychiatristLogin psychiatristLogin) {
		String msg = "";

		try {

			service.updateJewellery(psychiatristLogin);
			msg += "updateSuccess";

		} catch (Exception e) {
			msg += "updateFailure";
		}
		return msg;
	}
	@PutMapping("/psychiatrist/{id}")
	public ResponseEntity<String> updatePsychiatrist(
	        @PathVariable("id") Long id, 
	        @RequestBody PsychiatristLogin psychiatristLogin) {
	    try {
	        // Ensure you are updating the correct record based on ID
	        //psychiatristLogin.setPsychiatristId(id); 
	        service.updateJewellery(psychiatristLogin);
	        return ResponseEntity.ok("updateSuccess");
	    } catch (Exception e) {
	        return null;
	    }
	}

	@GetMapping("{id}")
	public PsychiatristLogin getJewelleryById(@PathVariable("id") int id) {
		System.out.println("Nanthanda Athu"+id);
		return service.getJewellery(id);
	}
	
	@GetMapping("/findappointment/{id}")
	public List<Appointment> getAppointmentsById(@PathVariable("id") int id) {
		return service.getAllAppointment(id);
	}

	@GetMapping("/all")
	public List<PsychiatristLogin> getJewellery() {
		return service.getAllJewellery();
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
}
