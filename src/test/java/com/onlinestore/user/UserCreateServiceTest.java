package com.onlinestore.user;

import com.onlinestore.user.adresses.Address;
import com.onlinestore.user.adresses.AddressRepository;
import com.onlinestore.user.role.UserRole;
import com.onlinestore.user.role.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserCreateServiceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    AddressRepository addressRepository;

    UserRole savedUserRole;
    Address savedAddress;
    UserMapper userMapper;

    @BeforeEach
    void setUp() {
        addressRepository.deleteAll();
        userRepository.deleteAll();
        userRoleRepository.deleteAll();

        UserRole userRole = new UserRole();
        userRole.setUserRole("USER");
        savedUserRole = userRoleRepository.save(userRole);

        Address address = new Address();
        address.setCountry("Polska");
        address.setCity("Gdansk");
        address.setStreet("Grunwaldzka");
        address.setApartmentNumber("22");
        address.setHouseNumber("11");
        address.setPostalCode("80800");
        savedAddress = addressRepository.save(address);
    }

    @Test
    void userCreateServiceTest() {
        //given
//        when(userRepository.save(any(User.class))).thenReturn(new User());
        when(userRepository.save(any(User.class))).thenReturn(new User());
        //when
        User user = new User();
        user.setUsername("user@user.com");
        user.setPassword("user");
        user.setUserRole(new UserRole());
        user.setAvatar("avatar");
        user.setAddress(new Address());
        user.setContactPreference("email");

        UserDto result = userService.createUser(userMapper.mapToUserDto(user));


        //then
        verify(userRepository).save(any(User.class));
    }
}
