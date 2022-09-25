package xyz.imaf6971.volgaitfinal.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.imaf6971.volgaitfinal.dto.RegistrationDto;
import xyz.imaf6971.volgaitfinal.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public void registration(@RequestBody RegistrationDto registrationDto) {
        userService.register(registrationDto);
    }

    @PostMapping("/login")
    public void login(@RequestBody RegistrationDto registrationDto) {
        userService.login(registrationDto);
    }
}
