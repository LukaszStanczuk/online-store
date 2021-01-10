package com.onlinestore.user;

import com.onlinestore.user.adresses.Address;
import com.onlinestore.user.role.UserRole;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDefinition {
    private String username;
    private String password;
    private String avatar;
    private String contactPreference;
    private UserRole userRole;
    private Address address;
}
