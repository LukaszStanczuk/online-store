package com.onlinestore.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinestore.user.adresses.Address;
import com.onlinestore.user.adresses.AddressRepository;
import com.onlinestore.user.role.RolesConfiguration;
import com.onlinestore.user.role.UserRole;
import com.onlinestore.user.role.UserRoleRepository;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@NoArgsConstructor
class UserCreateIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserRoleRepository userRoleRepository;

    UserRole savedUserRole;
    Address savedAddress;
    RolesConfiguration rolesConfiguration;
    ObjectMapper objectMapper = new ObjectMapper();

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
    void createNewUser_createsNewUserAndReturn201StatusCode() throws Exception {
        //given
        UserDto userDto = new UserDto(
                null,
                "user@user.pl",
                "user",
                "foto",
                "email",
                savedUserRole,
                savedAddress);
        String requestBody = objectMapper.writeValueAsString(userDto);
        MockHttpServletRequestBuilder post = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        //when
        MvcResult result = mockMvc.perform(post).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());


    }

}
