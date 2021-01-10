package com.onlinestore.user;

import com.onlinestore.user.adresses.Address;
import com.onlinestore.user.userRole.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String username;
    private String password;
    private String avatar; //todo: url type?
    private String contactPreference;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private UserRole userRole;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    private Address address;
    @ToString.Exclude
    private String authorities;

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return Arrays.stream(authorities.split(";"))
                .map(s -> (GrantedAuthority) () -> s)
                .collect(Collectors.toList());
    }


    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(";"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
