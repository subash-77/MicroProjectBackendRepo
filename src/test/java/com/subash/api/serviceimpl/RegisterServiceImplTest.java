package com.subash.api.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.Instant;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.subash.api.model.PsychiatristLogin;
import com.subash.api.model.Token;
import com.subash.api.model.User;
import com.subash.api.ownrepo.RegisterOwnRepo;
import io.jsonwebtoken.Jwts;

class RegisterServiceImplTest {

    @Mock
    private RegisterOwnRepo ownrepo;

    @InjectMocks
    private RegisterServiceImpl registerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddRegister() {
        User user = new User();
        doNothing().when(ownrepo).save(user);

        registerService.addRegister(user);

        verify(ownrepo, times(1)).save(user);
    }

    @Test
    void testCheckCredentials() {
        String email = "test@example.com";
        String password = "password";
        User user = new User();
        user.setPassword(password);

        when(ownrepo.findByEmail(email)).thenReturn(user);

        assertTrue(registerService.checkCredentials(email, password));
        assertFalse(registerService.checkCredentials(email, "wrongpassword"));
    }

    @Test
    void testAddToken() {
        Token token = new Token();
        doNothing().when(ownrepo).saveToken(token);

        registerService.addToken(token);

        verify(ownrepo, times(1)).saveToken(token);
    }

    @Test
    void testIsTokenValid() {
        String randomValue = "randomValue";
        String tokenString = Jwts.builder()
            .setSubject("user")
            .setExpiration(Date.from(Instant.now().plusSeconds(60)))
            .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, "SUBASH")
            .compact();

        when(ownrepo.findByRandomValue(randomValue)).thenReturn(tokenString);

        assertTrue(registerService.isTokenValid(randomValue));

        when(ownrepo.findByRandomValue(randomValue)).thenReturn(null);
        assertFalse(registerService.isTokenValid(randomValue));

        when(ownrepo.findByRandomValue(randomValue)).thenReturn(tokenString);
        registerService.isTokenValid(randomValue);
    }

    @Test
    void testDeleteToken() {
        String randomValue = "randomValue";
        doNothing().when(ownrepo).deleteTokenIfExpiried(randomValue);

        registerService.deleteToken(randomValue);

        verify(ownrepo, times(1)).deleteTokenIfExpiried(randomValue);
    }

    @Test
    void testFindRole() {
        String email = "test@example.com";
        User user = new User();
        user.setRole("Admin");

        when(ownrepo.findByEmail(email)).thenReturn(user);

        assertEquals("Admin", registerService.findRole(email));
    }

    @Test
    void testFindPsyId() {
        String email = "test@example.com";
        User user = new User();
        PsychiatristLogin psychiatristLogin = new PsychiatristLogin();
        user.setPsychiatristLogin(psychiatristLogin);

        when(ownrepo.findPsyId(email)).thenReturn(user);

        assertEquals(psychiatristLogin, registerService.findPsyId(email));
    }

    @Test
    void testFindPatientId() {
        String email = "test@example.com";
        User user = new User();
        user.setUserId(123);

        when(ownrepo.findPatientId(email)).thenReturn(user);

        assertEquals(123, registerService.findPatientId(email));
    }
}
