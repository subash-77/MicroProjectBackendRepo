package com.subash.api.controller;

import java.util.List;

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
import com.subash.api.model.PatientInfo;
import com.subash.api.model.Payment;
import com.subash.api.model.PsychiatristLogin;
import com.subash.api.model.User;
import com.subash.api.service.PsychiatristService;
import com.subash.api.serviceimpl.AdminServiceImpl;
import com.subash.api.serviceimpl.EmailService;


@RestController
@RequestMapping("/admin")
@CrossOrigin("http://localhost:3000")

public class AdminController {

	private EmailService emailService;

	AdminServiceImpl service;

	
	PsychiatristService psychiatristService;
	

	public AdminController(EmailService emailService, AdminServiceImpl service,
			PsychiatristService psychiatristService) {
		super();
		this.emailService = emailService;
		this.service = service;
		this.psychiatristService = psychiatristService;
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

	@PostMapping("/updateAppointment")
	public String insertAppointment(@RequestBody Appointment appointment) {
		String msg = "";

		PatientInfo patientInfo = appointment.getPatientInfo();
		int patientId = patientInfo.getPatientinfoId();
		String patientEmail = service.getPatientEmail(patientId);


		String psychiatristId = appointment.getPsychiatristId();
		PsychiatristLogin user = psychiatristService.getJewellery(Integer.parseInt(psychiatristId));

		String psychiatristEmail = user.getEmail();

		String meetingLink = "https://meetinglink.example.com/constant-meeting-room";

		try {
			service.addAppointment(appointment);
			msg += "addSuccess";


			String appointmentDate = appointment.getAppointmentDate();
			String appointmentSlot = appointment.getAppointmentSlot();
			String slotTime = getSlotTime(appointmentSlot);


			String psychiatristText = String.format(
					"""
	                You have an appointment with a patient.
	                Appointment Date: %s
	                Appointment Slot: %s (%s)
	                Patient Info:
	                Name: %s
	                Phone No: %s
	                Gender: %s
	                Age: %s
	                Please click here to join the meeting: %s
	                """,
					appointmentDate, appointmentSlot, slotTime, patientInfo.getName(), patientInfo.getPhoneNo(),
					patientInfo.getGender(), patientInfo.getAge(), meetingLink);

			String subject = "Appointment Confirmation";
			emailService.sendEmail(psychiatristEmail, subject, psychiatristText);

			Payment payment = appointment.getPayment();

			String patientText = String.format(
					"""
	                Dear %s,
	                Your appointment has been scheduled.
	                Appointment Date: %s
	                Appointment Slot: %s (%s)
	                Payment Info:
	                Name: %s
	                Payment Date: %s
	                Payment Type: %s
	                Amount: %s
	                Please click here to join the meeting: %s
	                We look forward to seeing you.
	                Best regards,
	                Your Clinic
	                """,
					patientInfo.getName(), appointmentDate, appointmentSlot, slotTime, patientInfo.getName(),
					payment.getPaymentDate(), payment.getPaymentType(), payment.getAmount(), meetingLink);

			String patientSubject = "Appointment Confirmed";
			emailService.sendEmail(patientEmail, patientSubject, patientText);

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

	@GetMapping("{id}")
	public User getJewelleryById(@PathVariable("id") int id) {
		return service.getJewellery(id);
	}

	@GetMapping("/all")
	public List<User> getJewellery() {
		return service.getAllJewellery();
	}

	@GetMapping("/allPatients")
	public List<User> getAllPatients() {
		return service.getAllPatient();
	}

	@GetMapping("/allAppointment")
	public List<Appointment> getAllAppointment() {
		return service.getAllAppointment();
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

	private String getSlotTime(String slot) {
		switch (slot) {
		case "slot1":
			return "10:00 AM - 11:00 AM";
		case "slot2":
			return "02:00 PM - 03:00 PM";
		case "slot3":
			return "07:00 PM - 08:00 PM";
		default:
			return "Unknown Slot";
		}
	}
}
