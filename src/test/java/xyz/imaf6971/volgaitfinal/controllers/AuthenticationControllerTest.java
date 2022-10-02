package xyz.imaf6971.volgaitfinal.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xyz.imaf6971.volgaitfinal.dto.RegistrationDto;
import xyz.imaf6971.volgaitfinal.service.AuthenticationService;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {

    @Mock
    AuthenticationService auth;

    @Test
    void registration() {
        var underTest = new AuthenticationController(auth);

        var registration = new RegistrationDto("user", "pass");
        underTest.registration(registration);

        verify(auth, times(1)).register(eq(registration));
    }

    @Test
    void login() {
        var underTest = new AuthenticationController(auth);

        var login = new RegistrationDto("user", "pass");
        underTest.login(login);

        verify(auth, times(1)).login(eq(login));
    }
}