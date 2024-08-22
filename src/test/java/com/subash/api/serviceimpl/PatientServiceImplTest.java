package com.subash.api.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.subash.api.model.CarePlan;
import com.subash.api.model.EHR;
import com.subash.api.model.User;
import com.subash.api.ownrepo.PatientOwnRepo;

class PatientServiceImplTest {

    @InjectMocks
    private PatientServiceImpl patientService;

    @Mock
    private PatientOwnRepo ownrepo;

    private User sampleUser;
    private CarePlan sampleCarePlan;
    private EHR sampleEHR;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sampleUser = new User(1, "John Doe", "john.doe@example.com", "password", "Patient", Collections.emptyList(), null);
        sampleCarePlan = new CarePlan(1, "Psychiatrist1", "Patient1", "CareCard1", "Active");
        sampleEHR = new EHR(1, "Patient1", "Psychiatrist1", "2024-08-21", new byte[]{1, 2, 3}, "Description");
    }

    @Test
    void testAddJewelleryNull() {
        String result = patientService.addJewellery(null);

        assertEquals("Add Failure", result);
        verify(ownrepo, times(0)).save(any(User.class));
    }

    @Test
    void testGetJewellery() {
        when(ownrepo.findById(1)).thenReturn(sampleUser);

        User result = patientService.getJewellery(1);

        assertNotNull(result);
        assertEquals(1, result.getUserId());
        verify(ownrepo, times(1)).findById(1);
    }

    @Test
    void testGetAllJewellery() {
        when(ownrepo.findAll()).thenReturn(Collections.singletonList(sampleUser));

        List<User> result = patientService.getAllJewellery();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(ownrepo, times(1)).findAll();
    }

    @Test
    void testUpdateJewellery() {
        doNothing().when(ownrepo).update(any(User.class));

        patientService.updateJewellery(sampleUser);

        verify(ownrepo, times(1)).update(sampleUser);
    }

    @Test
    void testDeleteJewellery() {
        doNothing().when(ownrepo).deleteById(1);

        patientService.deleteJewellery(1);

        verify(ownrepo, times(1)).deleteById(1);
    }

    @Test
    void testGetAllAppointment() {
        when(ownrepo.findAllAppointment(1)).thenReturn(Collections.singletonList(sampleUser));

        List<User> result = patientService.getAllAppointment(1);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(ownrepo, times(1)).findAllAppointment(1);
    }

    @Test
    void testGetAllCarePlan() {
        when(ownrepo.findAllCarePlan(1)).thenReturn(Collections.singletonList(sampleCarePlan));

        List<CarePlan> result = patientService.getAllCarePlan(1);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(ownrepo, times(1)).findAllCarePlan(1);
    }

    @Test
    void testUpdateCarePlan() {
        doNothing().when(ownrepo).updateCarePlan(any(CarePlan.class));

        patientService.updateCarePlan(sampleCarePlan);

        verify(ownrepo, times(1)).updateCarePlan(sampleCarePlan);
    }

    @Test
    void testGetEHRsByPatientId() {
        when(ownrepo.getEHRsByPatientId("Patient1")).thenReturn(Collections.singletonList(sampleEHR));

        List<EHR> result = patientService.getEHRsByPatientId("Patient1");

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(sampleEHR, result.get(0));
        verify(ownrepo, times(1)).getEHRsByPatientId("Patient1");
    }
}
