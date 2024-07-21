package com.mustafatoker.motta.todo.exception;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(String s) {
        super(s);
    }
}
