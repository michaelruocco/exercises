package uk.co.mruoc.exercises.naughtsandcrosses.board;

import uk.co.mruoc.exercises.naughtsandcrosses.game.InvalidTurnException;

public class LocationNotFoundException extends InvalidTurnException {

    public LocationNotFoundException(String message) {
        super(String.format("location %s not found", message));
    }
}
