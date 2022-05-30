package uk.co.mruoc.exercises.naughtsandcrosses.game;

public class InvalidTurnException extends RuntimeException {

    public InvalidTurnException(String message) {
        super(message);
    }
}
