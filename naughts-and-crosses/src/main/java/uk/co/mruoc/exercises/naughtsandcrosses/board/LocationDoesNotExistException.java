package uk.co.mruoc.exercises.naughtsandcrosses.board;

public class LocationDoesNotExistException extends InvalidTurnException {

    public LocationDoesNotExistException(String message) {
        super(String.format("location %s does not exist", message));
    }
}
