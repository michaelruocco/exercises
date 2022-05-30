package uk.co.mruoc.exercises.naughtsandcrosses.locationselector;


import uk.co.mruoc.exercises.naughtsandcrosses.board.Board;

import java.util.function.Function;

public interface LocationSelector extends Function<Board, String> {

    default String selectLocation(Board board) {
        return apply(board);
    }
}
