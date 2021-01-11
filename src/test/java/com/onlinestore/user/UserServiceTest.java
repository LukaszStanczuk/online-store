package com.onlinestore.user;

import com.onlinestore.user.adresses.Address;
import com.onlinestore.user.role.RolesConfiguration;
import com.onlinestore.user.role.UserRole;
import com.onlinestore.user.role.UserRoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    RolesConfiguration rolesConfiguration;
    @Mock
    UserMapper userMapper;
    @Mock
    UserRoleRepository userRoleRepository;

    @InjectMocks
    UserService userService;

    @Test
    void userServiceTest_CreateUser() {
        //given
        when(userRepository.save(any(User.class))).thenReturn(new User());
        when(userMapper.mapToUserDto(any(User.class))).thenReturn(new UserDto());
        when(rolesConfiguration.getDefaultRole()).thenReturn(new String());
        when(rolesConfiguration.getDefaultRole()).thenReturn(new String());
        //when
        UserDto result = userService.createUser(UserDto.builder()
                .id(null)
                .username("user")
                .password("user")
                .avatar("foto")
                .contactPreference("email")
                .address(new Address())
                .userRole(new UserRole())
                .build());
        //then
        verify(userRepository).save(any(User.class));
    }
}
