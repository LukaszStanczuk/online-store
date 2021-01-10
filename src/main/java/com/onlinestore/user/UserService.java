package com.onlinestore.user;

import com.onlinestore.exception.NotFoundException;
import com.onlinestore.user.role.RolesConfiguration;
import com.onlinestore.user.role.UserRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Component
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RolesConfiguration rolesConfiguration;
    private final UserMapper userMapper;
    private UserRole userRole;

    UserDto createUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setAvatar(userDto.getAvatar());
        user.setUserRole(userDto.getUserRole());
        user.setContactPreference(userDto.getContactPreference());
        user.setAddress(userDto.getAddress());
        return userMapper.mapToUserDto(userRepository.save(user));
    }

    public User fetchUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bad id: " + id));
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    public UserDto changeRole(User user, String role) {
        rolesConfiguration.getRoles().stream().filter(x->x.equals(role))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Role not exists" + role));
        userRole.setUserRole(role);
        user.setUserRole(userRole);
        return userMapper.mapToUserDto(userRepository.save(user));
    }
}
