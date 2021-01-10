package com.onlinestore.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    UserDto mapToUserDto(User newUser) {
        return UserDto.builder()
                .id(newUser.getId())
                .username(newUser.getUsername())
                .password(newUser.getPassword())
                .avatar(newUser.getAvatar())
                .contactPreference(newUser.getContactPreference())
                .userRole(newUser.getUserRole())
                .address(newUser.getAddress())
                .build();
    }
}
