package com.onlinestore.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    UserDto mapToUserDto(User newUser) {
        UserDto userDto = new UserDto();
        userDto.setId(newUser.getId());
        userDto.setAddress(newUser.getAddress());
        userDto.setAvatar(newUser.getAvatar());
        userDto.setUsername(newUser.getUsername());
        userDto.setPassword(newUser.getPassword());
        userDto.setUserRole(newUser.getUserRole());
        userDto.setContactPreference(newUser.getContactPreference());
        return userDto;
    }
}
