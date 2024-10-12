package src.exceptions;

public class MaxCapacityException extends RuntimeException {
    public MaxCapacityException() {
        super("Please choose another time, this class is fully booked.");
    }
}
