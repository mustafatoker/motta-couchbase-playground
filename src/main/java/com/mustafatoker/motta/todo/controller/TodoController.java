package com.mustafatoker.motta.todo.controller;

import com.mustafatoker.motta.todo.dto.request.TodoCreateRequest;
import com.mustafatoker.motta.todo.dto.request.TodoUpdateRequest;
import com.mustafatoker.motta.todo.dto.response.TodoResponse;
import com.mustafatoker.motta.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponse> store(@Valid @RequestBody TodoCreateRequest todoRequest) {
        var todo = todoService.create(todoRequest);

        return ResponseEntity.ok(todo);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> getTodosByUserId() {
        return ResponseEntity.ok(todoService.getTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> show(@PathVariable String id) {
        return ResponseEntity.ok(todoService.getTodoById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable String id, @Valid @RequestBody TodoUpdateRequest todoRequest) {
        var todo = todoService.update(id, todoRequest);

        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable String id) {
        todoService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Void> markTodoAsCompleted(@PathVariable String id) {
        todoService.markTodoAsCompleted(id);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/incomplete")
    public ResponseEntity<Void> markTodoAsIncomplete(@PathVariable String id) {
        todoService.markTodoAsIncomplete(id);

        return ResponseEntity.ok().build();
    }
}