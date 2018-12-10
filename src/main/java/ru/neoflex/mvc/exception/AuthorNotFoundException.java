package ru.neoflex.mvc.exception;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(int id) {
        super("Could not find author with id '" + id + "'.");
    }

    public AuthorNotFoundException() {
    }

    public AuthorNotFoundException(String message) {
        super(message);
    }

    public AuthorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
