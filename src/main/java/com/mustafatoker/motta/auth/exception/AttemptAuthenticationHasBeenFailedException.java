package com.mustafatoker.motta.auth.exception;

public class AttemptAuthenticationHasBeenFailedException extends RuntimeException {
    public AttemptAuthenticationHasBeenFailedException(Throwable cause) {
        super(cause);
    }
}
