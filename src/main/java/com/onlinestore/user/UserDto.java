package com.onlinestore.user;

import com.onlinestore.annotation.ExistingAddress;
import com.onlinestore.annotation.ExistingRole;
import com.onlinestore.annotation.ExistingUser;
import com.onlinestore.user.adresses.Address;
import com.onlinestore.user.role.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

//    @ExistingUser
    private Long id;

    @Email
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @URL
    @NotBlank
    private String avatar;

    @NotBlank
    private String contactPreference;

//    @ExistingRole
    @NonNull
    private UserRole userRole;

//    @ExistingAddress
    @NonNull
    private Address address;
}
