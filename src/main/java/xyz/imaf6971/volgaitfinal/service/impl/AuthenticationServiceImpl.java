package xyz.imaf6971.volgaitfinal.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.imaf6971.volgaitfinal.dto.RegistrationDto;
import xyz.imaf6971.volgaitfinal.model.User;
import xyz.imaf6971.volgaitfinal.service.AuthenticationService;
import xyz.imaf6971.volgaitfinal.service.RoleService;
import xyz.imaf6971.volgaitfinal.service.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final RoleService roleService;

    public AuthenticationServiceImpl(
            UserService userService,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.roleService = roleService;
    }

    @Override
    public void register(RegistrationDto registrationDto) {
        var user = new User();
        user.setRole(roleService.getDefaultRole());
        user.setUsername(registrationDto.username());
        user.setPassword(passwordEncoder.encode(registrationDto.password()));
        user.setCanPublish(true);
        userService.saveUser(user);
    }

    @Override
    public void login(RegistrationDto registrationDto) {
        var authToken = new UsernamePasswordAuthenticationToken(
                registrationDto.username(),
                registrationDto.password()
        );
        var auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

}
