package by.incubator.infrastructure.exception;

public class ConstructorNotFoundException extends RuntimeException {
    public ConstructorNotFoundException(String message) {
        super(message);
    }
}
