package uk.co.mruoc.exercises.channelprocessing.parameter;

public class ParameterNotFoundException extends RuntimeException {

    public ParameterNotFoundException(char message) {
        super(Character.toString(message));
    }

}
