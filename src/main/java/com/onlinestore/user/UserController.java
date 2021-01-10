package com.onlinestore.user;

import com.onlinestore.user.userRole.Roles;
import com.onlinestore.user.userRole.UserRole;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserFetchService userFetchService;
    private final UserMapper userMapper;
    private final UserCreateService userCreateService;

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
        userRole.setUserRole(Roles.ROLE_USER);
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
}
