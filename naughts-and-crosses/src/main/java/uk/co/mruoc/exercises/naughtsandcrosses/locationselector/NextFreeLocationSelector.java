package uk.co.mruoc.exercises.naughtsandcrosses.locationselector;

import uk.co.mruoc.exercises.naughtsandcrosses.board.Board;

public class NextFreeLocationSelector implements LocationSelector {

    @Override
    public String apply(Board board) {
        return board.getNextFreeLocation();
    }
}
