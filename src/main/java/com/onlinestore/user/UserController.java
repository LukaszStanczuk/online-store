package com.onlinestore.user;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.onlinestore.user.role.RolesConfiguration;
import com.onlinestore.user.role.UserRole;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserFetchService userFetchService;
    private final UserMapper userMapper;
    private final UserCreateService userCreateService;
    private final RolesConfiguration rolesConfiguration;

    @AllArgsConstructor
    static class Users{
        private List<UserDto> users;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/users")
    public Users getAllUsers() {
        return new Users(userFetchService.getAllUsers());
    }

//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/users/{id}")
    public UserDto getUser(@PathVariable long id) {
        User user = userFetchService.fetchUser(id);
        return userMapper.mapToUserDto(user);
    }


    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        UserRole userRole = new UserRole();
        userRole.setUserRole(rolesConfiguration.getDefaultRole());
        UserDefinition userDefinition = UserDefinition.builder()
                .address(userDto.getAddress())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .avatar(userDto.getAvatar())
                .contactPreference(userDto.getContactPreference())
                .userRole(userRole)
                .build();

        User user = userCreateService.createUser(userDefinition);

        return userMapper.mapToUserDto(user);
    }

    @PostMapping("/users/{userId}/roles/{role}")
    public void addRoleToUser(@PathVariable Long userId, @PathVariable String role) {
        final User user = userFetchService.fetchUser(userId);
        //
        rolesConfiguration.getRoles().stream().filter(x -> x.equals(role))
            .findFirst()
            .orElseThrow();
        //
        final UserRole userRole = new UserRole();
        userRole.setUserRole(role);
        user.setUserRole(userRole);
    }
}
