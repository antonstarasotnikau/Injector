package by.incubator.infrastructure.exception;

public class TooManyConstructorsException extends RuntimeException {
    public TooManyConstructorsException(String message) {
        super(message);
    }
}
