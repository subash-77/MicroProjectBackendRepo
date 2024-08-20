package com.subash.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.subash.api.serviceimpl.PatientServiceImpl;
import com.subash.api.serviceimpl.PsychiatristServiceImpl;

@RestController
@RequestMapping("/admin")
@CrossOrigin("http://localhost:3000")

public class AdminController {

	  @Autowired
	  private EmailService emailService;
	  
	AdminServiceImpl service;
	
	@Autowired
	PsychiatristService psychiatristService;

	public AdminController(AdminServiceImpl service) {
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

	@PostMapping("/updateAppointment")
	public String insertAppointment(@RequestBody Appointment appointment) {
	    String msg = "";

	    PatientInfo patientInfo = appointment.getPatientInfo();
	    int patientId = patientInfo.getPatientinfoId();
	    String patientEmail = service.getPatientEmail(patientId);
	    
	    System.out.println("PATIENT EMAIL: " + patientEmail);

	    String psychiatristId = appointment.getPsychiatristId();
	    PsychiatristLogin user = psychiatristService.getJewellery(Integer.parseInt(psychiatristId)); 

	    String psychiatristEmail = user.getEmail();

	    // Define the constant meeting link
	    String meetingLink = "https://meetinglink.example.com/constant-meeting-room";

	    try {
	        service.addAppointment(appointment);
	        msg += "addSuccess";
	        
	        // Extract details for the email
	        String appointmentDate = appointment.getAppointmentDate();
	        String appointmentSlot = appointment.getAppointmentSlot();
	        String slotTime = getSlotTime(appointmentSlot);

	        // Format email text for psychiatrist
	        String psychiatristText = String.format(
	            "You have an appointment with a patient.\n\n" +
	            "Appointment Date: %s\n" +
	            "Appointment Slot: %s (%s)\n\n" +
	            "Patient Info:\n" +
	            "Name: %s\n" +
	            "Phone No: %s\n" +
	            "Gender: %s\n" +
	            "Age: %s\n\n" +
	            "Please click here to join the meeting: %s",
	            appointmentDate,
	            appointmentSlot,
	            slotTime,
	            patientInfo.getName(),
	            patientInfo.getPhoneNo(),
	            patientInfo.getGender(),
	            patientInfo.getAge(),
	            meetingLink
	        );

	        String subject = "Appointment Confirmation";
	        emailService.sendEmail(psychiatristEmail, subject, psychiatristText);
	        
	        Payment payment = appointment.getPayment();
	        
	        // Format email text for patient
	        String patientText = String.format(
	            "Dear %s,\n\n" +
	            "Your appointment has been scheduled.\n\n" +
	            "Appointment Date: %s\n" +
	            "Appointment Slot: %s (%s)\n\n" +
	            "Payment Info:\n" +
	            "Name: %s\n" +
	            "Payment Date: %s\n" +
	            "Payment Type: %s\n" +
	            "Amount: %s\n\n" +
	            "Please click here to join the meeting: %s\n\n" +
	            "We look forward to seeing you.\n" +
	            "Best regards,\n" +
	            "Your Clinic",
	            patientInfo.getName(),
	            appointmentDate,
	            appointmentSlot,
	            slotTime,
	            patientInfo.getName(),
	            payment.getPaymentDate(),
	            payment.getPaymentType(),
	            payment.getAmount(),
	            meetingLink
	        );

	        // Send email to patient
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
