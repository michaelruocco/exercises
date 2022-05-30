package uk.co.mruoc.exercises.naughtsandcrosses.board;

public class InvalidTurnException extends RuntimeException {

    public InvalidTurnException(String message) {
        super(message);
    }
}
