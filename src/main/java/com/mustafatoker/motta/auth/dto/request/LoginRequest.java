package com.mustafatoker.motta.auth.dto.request;

import com.mustafatoker.motta.auth.domain.Username;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank Username username,
        @NotBlank String password
) {
}
