package ru.neoflex.mvc.exception;

public class ThemeNotFoundException extends RuntimeException {

    public ThemeNotFoundException(int id) {
        super("Could not find theme with id '" + id + "'.");
    }

    public ThemeNotFoundException() {
    }

    public ThemeNotFoundException(String message) {
        super(message);
    }

    public ThemeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
