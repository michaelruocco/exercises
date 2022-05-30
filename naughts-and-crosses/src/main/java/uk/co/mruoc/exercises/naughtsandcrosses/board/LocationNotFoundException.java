package uk.co.mruoc.exercises.naughtsandcrosses.board;

public class LocationNotFoundException extends InvalidTurnException {

    public LocationNotFoundException(String message) {
        super(String.format("location %s not found", message));
    }
}
