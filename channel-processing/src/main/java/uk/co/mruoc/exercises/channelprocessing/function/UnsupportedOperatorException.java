package uk.co.mruoc.exercises.channelprocessing.function;

public class UnsupportedOperatorException extends RuntimeException {

    public UnsupportedOperatorException(char operator) {
        super(Character.toString(operator));
    }
}
