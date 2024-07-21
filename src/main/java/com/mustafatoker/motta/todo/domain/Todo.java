package com.mustafatoker.motta.todo.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Document
public class Todo implements Serializable {
    @Id
    private final String id;

    @Field
    private final String userId;

    @Field
    private final String title;

    @Field
    private final String description;

    @Field
    private final boolean completed;

    @Field(name = "created_at")
    private final LocalDateTime createdAt;

    @Field(name = "due_date")
    private final LocalDateTime dueDate;

    @Field(name = "priority")
    private final Priority priority;

    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    private Todo(String id, String userId, String title, String description, boolean completed, LocalDateTime createdAt,
                 LocalDateTime dueDate, Priority priority) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.createdAt = createdAt;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public static Todo of(String userId, String title, String description, LocalDateTime dueDate, Priority priority) {
        return new Todo(UUID.randomUUID().toString(), userId, title, description, false, LocalDateTime.now(), dueDate, priority);
    }

    public Todo update(String title, String description, boolean completed, LocalDateTime dueDate, Priority priority) {
        return new Todo(id, userId, title, description, completed, createdAt, dueDate, priority);
    }

    public Todo markAsCompleted() {
        return new Todo(this.id, this.userId, this.title, this.description, true, this.createdAt, this.dueDate, this.priority);
    }

    public Todo markAsIncomplete() {
        return new Todo(this.id, this.userId, this.title, this.description, false, this.createdAt, this.dueDate, this.priority);
    }
}