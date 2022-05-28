package uk.co.mruoc.exercises.naughtsandcrosses.board;

import uk.co.mruoc.exercises.naughtsandcrosses.board.Board;
import uk.co.mruoc.exercises.naughtsandcrosses.board.Location;

import java.util.function.Function;

public interface SelectLocationStrategy extends Function<Board, Location> {

    default Location selectLocation(Board board) {
        return apply(board);
    }
}
