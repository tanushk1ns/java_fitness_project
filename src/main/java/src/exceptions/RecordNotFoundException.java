package src.exceptions;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String message) {
        super("A person named " + message + " is not registered for the class");
    }
}
