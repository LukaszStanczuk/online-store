package com.onlinestore.user;

import com.onlinestore.user.adresses.Address;
import com.onlinestore.user.role.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String avatar;
    private String contactPreference;
    private UserRole userRole;
    private Address address;
}
