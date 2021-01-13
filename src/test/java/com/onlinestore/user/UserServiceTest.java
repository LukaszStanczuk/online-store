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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
        Address address = new Address();
        address.setCountry("Poland");
        address.setCity("Gdansk");
        address.setStreet("Dluga");
        address.setHouseNumber("19");
        address.setHouseNumber("20");
        address.setPostalCode("80800");

        UserRole userRole = new UserRole();
        userRole.setUserRole("USER");


        when(userRepository.save(any(User.class))).thenReturn(new User());
        when(userMapper.mapToUserDto(any(User.class))).thenReturn(new UserDto());
        when(rolesConfiguration.getDefaultRole()).thenReturn(new String());
        when(userRoleRepository.findByUserRole(any(String.class))).thenReturn(Optional.of(userRole));
        //when
        UserDto result = userService.createUser(UserDto.builder()
                .id(null)
                .username("user@user.com")
                .password("user")
                .avatar("www.foto.pl")
                .contactPreference("email")
                .address(address)
                .userRole(userRole)
                .build());
        //then
        assertThat(result).isInstanceOf(UserDto.class);
        verify(userRepository).save(any(User.class));
    }
}
