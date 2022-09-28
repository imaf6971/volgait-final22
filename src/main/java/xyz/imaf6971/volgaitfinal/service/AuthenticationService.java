package xyz.imaf6971.volgaitfinal.service;

import xyz.imaf6971.volgaitfinal.dto.RegistrationDto;

public interface AuthenticationService {

    void register(RegistrationDto registrationDto);

    void login(RegistrationDto registrationDto);

}
