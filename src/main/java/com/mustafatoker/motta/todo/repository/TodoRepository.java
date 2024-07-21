package com.mustafatoker.motta.todo.repository;

import com.mustafatoker.motta.todo.domain.Todo;
import com.mustafatoker.motta.todo.exception.TodoNotFoundException;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends CouchbaseRepository<Todo, String> {

    List<Todo> findAllByUserId(String currentUserId);

    Optional<Todo> findByIdAndUserId(String id, String userId);

    boolean existsByIdAndUserId(String id, String userId);

    default Todo findByIdOrThrow(String id, String userId) {
        return findByIdAndUserId(id, userId).orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id));
    }
}
