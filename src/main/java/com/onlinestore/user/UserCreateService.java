package com.onlinestore.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreateService {

    private final UserRepository userRepository;

    User createUser(UserDefinition userDefinition) {
        User user = new User();
        user.setUsername(userDefinition.getUsername());
        user.setPassword(userDefinition.getPassword());
        user.setAvatar(userDefinition.getAvatar());
        user.setUserRole(userDefinition.getUserRole());
        user.setContactPreference(userDefinition.getContactPreference());
        user.setAddress(userDefinition.getAddress());
        return userRepository.save(user);
    }
}
