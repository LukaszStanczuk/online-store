package com.onlinestore.user;

import com.onlinestore.user.adresses.Address;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserCreateServiceTest {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserCreateService userCreateService;

    @Test
    void userCreateServiceTest() {
        //given
        when(userRepository.save(any(User.class))).thenReturn(new User());

        //when
        User result = userCreateService.createUser(UserDefinition.builder()
                .username("user")
                .password("user")
                .avatar("foto")
                .contactPreference("email")
                .address(new Address())
                .userRole(UserRole.ROLE_USER)
                .build());

        //then
        verify(userRepository).save(any(User.class));
    }

}
