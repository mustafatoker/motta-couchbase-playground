package com.mustafatoker.motta.todo.dto.response;

import com.mustafatoker.motta.todo.domain.Todo;

import java.time.LocalDateTime;

public record TodoResponse(String id, String userId, String title, String description, boolean completed,
                           LocalDateTime createdDate, LocalDateTime dueDate, Todo.Priority priority) {
    public static TodoResponse fromEntity(Todo todo) {
        return new TodoResponse(
                todo.getId(),
                todo.getUserId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.isCompleted(),
                todo.getCreatedAt(),
                todo.getDueDate(),
                todo.getPriority()
        );
    }
}
