package com.subash.api.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.subash.api.model.Appointment;
import com.subash.api.model.User;
import com.subash.api.ownrepoimpl.AdminOwnRepoImpl;

class AdminServiceImplTest {

    @InjectMocks
    private AdminServiceImpl adminService;

    @Mock
    private AdminOwnRepoImpl ownrepo;

    private User sampleUser;
    private Appointment sampleAppointment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sampleUser = new User(1, "John Doe", "john.doe@example.com", "password", "Admin", Collections.emptyList(), null);
        sampleAppointment = new Appointment(1, "Psychiatrist1", "2024-08-22", "10:00 AM", "Scheduled", null, null);
    }

    @Test
    void testAddJewelleryNull() {
        String result = adminService.addJewellery(null);

        assertEquals("Add Failure", result);
        verify(ownrepo, times(0)).save(any(User.class));
    }

    @Test
    void testGetJewellery() {
        when(ownrepo.findById(1)).thenReturn(sampleUser);

        User result = adminService.getJewellery(1);

        assertNotNull(result);
        assertEquals(1, result.getUserId());
        verify(ownrepo, times(1)).findById(1);
    }

    @Test
    void testGetAllJewellery() {
        when(ownrepo.findAll()).thenReturn(Collections.singletonList(sampleUser));

        var result = adminService.getAllJewellery();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(ownrepo, times(1)).findAll();
    }

    @Test
    void testGetAllPatient() {
        when(ownrepo.findAllPatients()).thenReturn(Collections.singletonList(sampleUser));

        var result = adminService.getAllPatient();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(ownrepo, times(1)).findAllPatients();
    }

    @Test
    void testUpdateJewellery() {
        doNothing().when(ownrepo).update(any(User.class));

        adminService.updateJewellery(sampleUser);

        verify(ownrepo, times(1)).update(sampleUser);
    }

    @Test
    void testDeleteJewellery() {
        doNothing().when(ownrepo).deleteById(1);

        adminService.deleteJewellery(1);

        verify(ownrepo, times(1)).deleteById(1);
    }

    @Test
    void testGetAllAppointment() {
        when(ownrepo.findAllAppointment()).thenReturn(Collections.singletonList(sampleAppointment));

        var result = adminService.getAllAppointment();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(ownrepo, times(1)).findAllAppointment();
    }



    @Test
    void testAddAppointmentNull() {
        String result = adminService.addAppointment(null);

        assertEquals("Add Failure", result);
        verify(ownrepo, times(0)).saveAppointment(any(Appointment.class));
    }

    @Test
    void testGetPatientEmail() {
        when(ownrepo.getPatientEmail(1)).thenReturn("john.doe@example.com");

        String result = adminService.getPatientEmail(1);

        assertEquals("john.doe@example.com", result);
        verify(ownrepo, times(1)).getPatientEmail(1);
    }
}
