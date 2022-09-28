package xyz.imaf6971.volgaitfinal.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.imaf6971.volgaitfinal.dto.RegistrationDto;
import xyz.imaf6971.volgaitfinal.model.User;
import xyz.imaf6971.volgaitfinal.repository.UserRepository;
import xyz.imaf6971.volgaitfinal.service.RoleService;
import xyz.imaf6971.volgaitfinal.service.UserService;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, RoleService roleService) {
        this.userRepository = userRepository;
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
        userRepository.save(user);
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

    @Override
    public User getCurrentUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return getByUsername(auth.getName());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = getByUsername(username);
        return toUserDetails(user);
    }

    private UserDetails toUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), rolesToAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> rolesToAuthorities(User user) {
        var authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().getTitle());
        return Collections.singletonList(authority);
    }

    private User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
    }

}
