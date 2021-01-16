package com.onlinestore.user;

import com.onlinestore.user.adresses.Address;
import com.onlinestore.user.adresses.AddressRepository;
import com.onlinestore.user.role.UserRole;
import com.onlinestore.user.role.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UserServiceIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserRoleRepository userRoleRepository;

    UserRole savedUserRole;
    User savedUser;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
        addressRepository.deleteAll();

        Address address = new Address();
        address.setCountry("Polska");
        address.setCity("Gdansk");
        address.setStreet("Grunwaldzka");
        address.setApartmentNumber("22");
        address.setHouseNumber("11");
        address.setPostalCode("80800");


        UserRole userRole = new UserRole();
        userRole.setUserRole("USER");

        UserRole adminRole = new UserRole();
        adminRole.setId(2l);
        adminRole.setUserRole("ADMIN");
        savedUserRole = userRoleRepository.save(adminRole);

        User user = new User();
        user.setUsername("user@user.com");
        user.setPassword("user");
        user.setUserRole(userRole);
        user.setAvatar("http://www.avatar.com");
        user.setAddress(address);
        user.setContactPreference("email");
        savedUser = userRepository.save(user);
    }

    @Test
    @WithMockUser(value = "test", roles = "ADMIN")
    void fetchUserDetails_returnsDetailsOfUser() throws Exception {
        //given
        Long userId = savedUser.getId();

        MockHttpServletRequestBuilder request = get("/users/" + userId)
                .contentType(MediaType.APPLICATION_JSON);

        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<User> users = userRepository.findAll();
        assertThat(users.size()).isEqualTo(1);
        assertThat(users.get(0)).satisfies(p -> {
            assertThat(p.getId()).isNotNull();
            assertThat(p.getAddress()).isNotNull();
            assertThat(p.getAvatar()).isNotNull();
            assertThat(p.getUsername()).isNotNull();
            assertThat(p.getPassword()).isNotNull();
            assertThat(p.getUserRole()).isNotNull();
            assertThat(p.getContactPreference()).isNotNull();
        });
    }

    @Test
    void fetchUserDetails_whenRepositoryIsEmpty_returnInformationAboutEmptyRepository() throws Exception {
        //given
        List<User> users = userRepository.findAll();
        MockHttpServletRequestBuilder request = get("/users/" + users.size() + 1)
                .contentType(MediaType.APPLICATION_JSON);

        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }


    @Test
    void fetchAllUsersDetails_returnsDetailsOfAllUsers() throws Exception {
        //given
        MockHttpServletRequestBuilder request = get("/users")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<User> users = userRepository.findAll();
        assertThat(users.size()).isEqualTo(1);
        assertThat(users.get(0)).satisfies(p -> {
            assertThat(p.getId()).isNotNull();
            assertThat(p.getAddress()).isNotNull();
            assertThat(p.getAvatar()).isNotNull();
            assertThat(p.getUsername()).isNotNull();
            assertThat(p.getPassword()).isNotNull();
            assertThat(p.getUserRole()).isNotNull();
            assertThat(p.getContactPreference()).isNotNull();
        });
    }

    @Test
    void addRoleToUser_returnsUserWithAdminRole() throws Exception {
        //given
        Long userId = savedUser.getId();
        MockHttpServletRequestBuilder request = post("/users/" + userId + "/roles/" + savedUserRole.getUserRole())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<User> users = userRepository.findAll();
        assertThat(users.get(0)).satisfies(p -> {
            assertThat(p.getUserRole().getUserRole()).isEqualTo(savedUserRole.getUserRole());
        });
    }
}
