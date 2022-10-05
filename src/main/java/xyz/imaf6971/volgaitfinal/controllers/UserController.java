package xyz.imaf6971.volgaitfinal.controllers;

import org.springframework.web.bind.annotation.*;
import xyz.imaf6971.volgaitfinal.dto.ChangeUsernameDto;
import xyz.imaf6971.volgaitfinal.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/{username}")
    public void changeUsername(
            @PathVariable String username,
            @RequestBody ChangeUsernameDto dto) {
        userService.changeUsername(username, dto.newUsername());
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }

}
