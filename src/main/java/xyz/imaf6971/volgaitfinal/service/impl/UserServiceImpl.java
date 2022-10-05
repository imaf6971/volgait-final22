package xyz.imaf6971.volgaitfinal.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.imaf6971.volgaitfinal.model.User;
import xyz.imaf6971.volgaitfinal.repository.UserRepository;
import xyz.imaf6971.volgaitfinal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getCurrentUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return getByUsername(auth.getName());
    }

    @Override
    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
    }

    @Override
    public void saveUser(User user) {
        repository.save(user);
    }

    @Override
    public void changeUsername(String username, String newUsername) {
        var user = getByUsername(username);
        user.setUsername(newUsername);
        saveUser(user);
    }

    @Override
    public void deleteUser(String username) {
        repository.deleteByUsername(username);
    }

}
