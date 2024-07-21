package com.mustafatoker.motta.auth.dto.request;

import com.mustafatoker.motta.auth.domain.Username;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
        @NotNull(message = "Username is mandatory") Username username,
        @NotNull(message = "Password is mandatory") String password
) {

}
