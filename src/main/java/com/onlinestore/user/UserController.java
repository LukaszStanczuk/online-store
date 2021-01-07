package com.onlinestore.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserFetchService userFetchService;
    private final UserMapper userMapper;
    private final UserCreateService userCreateService;
    private final UserRole USER_ROLE = UserRole.ROLE_USER;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userFetchService.getAllUsers()
                .stream()
                .map(p -> userMapper.mapToUserDto(p))
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/user/{id}")
    public UserDto getUser(@PathVariable long id) {
        User user = userFetchService.fetchUser(id);
        return userMapper.mapToUserDto(user);
    }


    @PostMapping("/adduser")
    ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDefinition userDefinition = UserDefinition.builder()
                .address(userDto.getAddress())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .avatar(userDto.getAvatar())
                .contactPreference(userDto.getContactPreference())
                .userRole(USER_ROLE)
                .build();

        User user = userCreateService.createUser(userDefinition);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userMapper.mapToUserDto(user));
    }
}
