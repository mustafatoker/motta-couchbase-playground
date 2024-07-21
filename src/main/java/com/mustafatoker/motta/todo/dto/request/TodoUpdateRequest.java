package com.mustafatoker.motta.todo.dto.request;

import com.mustafatoker.motta.todo.domain.Todo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TodoUpdateRequest(
        @NotBlank String title,
        String description,
        @NotNull boolean completed,
        @NotNull LocalDateTime dueDate,
        @NotNull Todo.Priority priority
) {
}
