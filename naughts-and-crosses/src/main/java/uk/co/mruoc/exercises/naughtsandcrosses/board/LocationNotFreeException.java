package uk.co.mruoc.exercises.naughtsandcrosses.board;

public class LocationNotFreeException extends InvalidTurnException {

    public LocationNotFreeException(String message) {
        super(String.format("location %s not free", message));
    }
}
