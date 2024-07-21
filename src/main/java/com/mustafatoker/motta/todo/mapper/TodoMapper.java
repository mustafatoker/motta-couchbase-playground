package com.mustafatoker.motta.todo.mapper;

import com.mustafatoker.motta.todo.domain.Todo;
import com.mustafatoker.motta.todo.dto.response.TodoResponse;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {

    public TodoResponse toResponse(Todo todo) {
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
