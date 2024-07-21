package com.mustafatoker.motta.auth.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serial;
import java.io.Serializable;
import java.util.regex.Pattern;

public class Username implements Serializable {
    @Serial
    private static final long serialVersionUID = 1905122041950251207L;
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-z0-9]+(?:\\.[a-z0-9]+)*$");
    private final String value;

    private Username(String value) {
        if (!USERNAME_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid username format");
        }
        this.value = value.toLowerCase();
    }

    @JsonCreator
    public static Username of(String value) {
        return new Username(value);
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}