package ru.iot.client.auth;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.iot.client.domain.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class UserDetailsImpl implements UserDetails {

    @Getter
    private final UUID id;

    @Getter
    private final String username;

    @Getter
    private final String password;

    @Getter
    Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(User user) {
        this.id = user.getId();
        this.username = user.getLogin();
        this.password = user.getPassword();
        this.authorities = new ArrayList<>();
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