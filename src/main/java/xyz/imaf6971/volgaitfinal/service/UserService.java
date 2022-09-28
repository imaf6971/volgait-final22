package xyz.imaf6971.volgaitfinal.service;

import xyz.imaf6971.volgaitfinal.dto.RegistrationDto;
import xyz.imaf6971.volgaitfinal.model.User;

public interface UserService {
    void register(RegistrationDto registrationDto);

    void login(RegistrationDto registrationDto);

    User getCurrentUser();
}
