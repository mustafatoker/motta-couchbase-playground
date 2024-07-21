package com.mustafatoker.motta.auth.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Document
public class User implements UserDetails {
    @Id
    private final String id;

    @Field
    private final Username username;

    @Field
    private final Password password;

    private User(String id, Username username, Password password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public static User of(Username username, Password password) {
        return new User(UUID.randomUUID().toString(), username, password);
    }

    public static User from(String id, Username username, Password password) {
        return new User(id, username, password);
    }

    public boolean checkPassword(String rawPassword, PasswordEncoder passwordEncoder) {
        return password.matches(rawPassword, passwordEncoder);
    }

    // The methods of UserDetails interface
    @Override
    public String getUsername() {
        return username.getValue();
    }

    @Override
    public String getPassword() {
        return password.getValue();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
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