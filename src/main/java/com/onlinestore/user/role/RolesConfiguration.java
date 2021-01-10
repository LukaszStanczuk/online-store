package com.onlinestore.user.role;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.AssertTrue;
import java.util.Set;

@Data
@Component
@Validated
@ConfigurationProperties(prefix = "online-store")
public class RolesConfiguration {
    private Set<String> roles;
    private String defaultRole;

    @AssertTrue
    public boolean isDefaultRoleShouldBeInRoles() {
        if (defaultRole == null) {
            return false;
        }
        return roles.contains(defaultRole);
    }
}
