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

import com.subash.api.model.Appointment;
import com.subash.api.model.EHR;
import com.subash.api.model.PsychiatristLogin;
import com.subash.api.ownrepo.PsychiatristOwnRepo;

class PsychiatristServiceImplTest {

    @InjectMocks
    private PsychiatristServiceImpl psychiatristService;

    @Mock
    private PsychiatristOwnRepo ownrepo;

    private PsychiatristLogin samplePsychiatristLogin;
    
    private EHR sampleEHR;
    private Appointment sampleAppointment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        samplePsychiatristLogin = new PsychiatristLogin(
            1, "Dr. Smith", "dr.smith@example.com", "password", 
            "Psychiatrist", "10", "Experienced", "MD", "$200",
            null
        );
        sampleEHR = new EHR(1, "Patient1", "Psychiatrist1", "2024-08-21", new byte[]{1, 2, 3}, "Description");
        sampleAppointment = new Appointment(); // Populate with necessary fields if needed
    }

    @Test
    void testGetJewellery() {
        when(ownrepo.findById(1)).thenReturn(samplePsychiatristLogin);

        PsychiatristLogin result = psychiatristService.getJewellery(1);

        assertNotNull(result);
        assertEquals(1, result.getPsychiatristId());
        verify(ownrepo, times(1)).findById(1);
    }

    @Test
    void testGetAllJewellery() {
        when(ownrepo.findAll()).thenReturn(Collections.singletonList(samplePsychiatristLogin));

        List<PsychiatristLogin> result = psychiatristService.getAllJewellery();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(ownrepo, times(1)).findAll();
    }

    @Test
    void testUpdateJewellery() {
        doNothing().when(ownrepo).update(samplePsychiatristLogin);

        psychiatristService.updateJewellery(samplePsychiatristLogin);

        verify(ownrepo, times(1)).update(samplePsychiatristLogin);
    }

    @Test
    void testDeleteJewellery() {
        doNothing().when(ownrepo).deleteById(1);

        psychiatristService.deleteJewellery(1);

        verify(ownrepo, times(1)).deleteById(1);
    }

    @Test
    void testGetAllAppointment() {
        when(ownrepo.findAllAppointment(1)).thenReturn(Collections.singletonList(sampleAppointment));

        List<Appointment> result = psychiatristService.getAllAppointment(1);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(ownrepo, times(1)).findAllAppointment(1);
    }


    @Test
    void testSaveEHR() {
        when(ownrepo.saveEhr(sampleEHR)).thenReturn(sampleEHR);

        EHR result = psychiatristService.saveEHR(sampleEHR);

        assertNotNull(result);
        assertEquals(sampleEHR, result);
        verify(ownrepo, times(1)).saveEhr(sampleEHR);
    }
}
