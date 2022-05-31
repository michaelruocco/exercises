package uk.co.mruoc.exercises.naughtsandcrosses.board;

public class LocationAlreadyTakenException extends InvalidTurnException {

    public LocationAlreadyTakenException(String message) {
        super(String.format("location %s already taken", message));
    }
}
