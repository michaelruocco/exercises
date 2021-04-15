package uk.co.mruoc.exercises.cronparser;

public class InvalidValueException extends RuntimeException {

    public InvalidValueException(int value, TimeUnit unit) {
        this(Integer.toString(value), unit);
    }

    public InvalidValueException(String value, TimeUnit unit) {
        super(String.format("invalid %s value %s", unit.name(), value));
    }

}
