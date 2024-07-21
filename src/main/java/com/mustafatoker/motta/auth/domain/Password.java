package com.mustafatoker.motta.auth.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serial;
import java.io.Serializable;

public class Password implements Serializable {
    @Serial
    private static final long serialVersionUID = 1905122041950251207L;
    private static final int MIN_LENGTH = 6;
    private static final int MAX_LENGTH = 32;

    private final String value;

    private Password(String value) {
        this.value = value;
    }

    public static Password ofRaw(String rawValue, PasswordEncoder passwordEncoder) {
        if (rawValue.length() < MIN_LENGTH || rawValue.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("Password must be between " + MIN_LENGTH + " and " + MAX_LENGTH + " characters");
        }
        return new Password(passwordEncoder.encode(rawValue));
    }

    @JsonCreator
    public static Password ofEncoded(String encodedValue) {
        return new Password(encodedValue);
    }

    public boolean matches(String rawPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(rawPassword, this.value);
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
