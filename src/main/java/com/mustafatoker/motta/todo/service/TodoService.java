package com.mustafatoker.motta.todo.service;

import com.mustafatoker.motta.todo.domain.Todo;
import com.mustafatoker.motta.todo.dto.request.TodoCreateRequest;
import com.mustafatoker.motta.todo.dto.request.TodoUpdateRequest;
import com.mustafatoker.motta.todo.dto.response.TodoResponse;
import com.mustafatoker.motta.todo.exception.TodoNotFoundException;
import com.mustafatoker.motta.todo.mapper.TodoMapper;
import com.mustafatoker.motta.todo.repository.TodoRepository;
import com.mustafatoker.motta.auth.service.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;
    private final CurrentUserService currentUserService;

    public TodoResponse create(TodoCreateRequest todoRequest) {
        var todo = Todo.of(
                currentUserService.getCurrentUserId(),
                todoRequest.title(),
                todoRequest.description(),
                todoRequest.dueDate(),
                todoRequest.priority());

        var persistedTodo = todoRepository.save(todo);

        return todoMapper.toResponse(persistedTodo);
    }

    public List<TodoResponse> getTodos() {
        return todoRepository.findAllByUserId(currentUserService.getCurrentUserId())
                .stream()
                .map(todoMapper::toResponse)
                .toList();
    }

    public TodoResponse getTodoById(String id) {
        var todo = todoRepository.findByIdOrThrow(id, currentUserService.getCurrentUserId());

        return todoMapper.toResponse(todo);
    }

    public TodoResponse update(String id, TodoUpdateRequest todoRequest) {
        var todo = todoRepository.findByIdOrThrow(id, currentUserService.getCurrentUserId());
        var updatedTodo = todo.update(
                todoRequest.title(),
                todoRequest.description(),
                todoRequest.completed(),
                todoRequest.dueDate(),
                todoRequest.priority());

        return todoMapper.toResponse(todoRepository.save(updatedTodo));
    }

    public void delete(String id) {
        if (!todoRepository.existsByIdAndUserId(id, currentUserService.getCurrentUserId())) {
            throw new TodoNotFoundException("Todo not found with id: " + id);
        }

        todoRepository.deleteById(id);
    }

    public void markTodoAsCompleted(String id) {
        var todo = todoRepository.findByIdOrThrow(id, currentUserService.getCurrentUserId());
        var completedTodo = todo.markAsCompleted();

        todoRepository.save(completedTodo);
    }

    public void markTodoAsIncomplete(String id) {
        var todo = todoRepository.findByIdOrThrow(id, currentUserService.getCurrentUserId());

        var incompleteTodo = todo.markAsIncomplete();
        todoRepository.save(incompleteTodo);
    }
}