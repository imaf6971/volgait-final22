package xyz.imaf6971.volgaitfinal.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.imaf6971.volgaitfinal.dto.RegistrationDto;
import xyz.imaf6971.volgaitfinal.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    private final AuthenticationService auth;

    public AuthenticationController(AuthenticationService auth) {
        this.auth = auth;
    }

    @PostMapping("/registration")
    public void registration(@RequestBody RegistrationDto registrationDto) {
        auth.register(registrationDto);
    }

    @PostMapping("/login")
    public void login(@RequestBody RegistrationDto registrationDto) {
        auth.login(registrationDto);
    }
}
