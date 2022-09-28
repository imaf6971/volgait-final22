package xyz.imaf6971.volgaitfinal.service;

import xyz.imaf6971.volgaitfinal.model.User;

public interface UserService {

    User getCurrentUser();

    User getByUsername(String username);

    void saveUser(User user);

}
