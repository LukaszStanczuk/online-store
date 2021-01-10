package com.onlinestore.user.userRole;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Set;

@Data
@Component
@ConfigurationProperties(prefix = "online-store")
public class RolesConfiguration {

    private Set<String> roles;

}
