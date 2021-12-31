package by.incubator.infrastructur.exception;

public class ConstructorNotFoundException extends RuntimeException {
    public ConstructorNotFoundException(String message) {
        super(message);
    }
}
