package com.onlinestore.user;

import com.onlinestore.user.role.RolesConfiguration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;
    private final RolesConfiguration rolesConfiguration;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/users")
    @Valid
    public Users getAllUsers() {
        return new Users(userService.getAllUsers());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/users/{id}")
    public UserDto getUser(@Valid @PathVariable long id) {
        final User user = userService.fetchUser(id);
        return userMapper.mapToUserDto(user);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/users/{userId}/roles/{role}")
    public UserDto addRoleToUser(@Valid @PathVariable Long userId, @Valid @PathVariable String role) {
        final User user = userService.fetchUser(userId);
        return userService.changeRole(user, role);
    }

    @Data
    @AllArgsConstructor
    static class Users {
        private List<UserDto> users;
    }
}
