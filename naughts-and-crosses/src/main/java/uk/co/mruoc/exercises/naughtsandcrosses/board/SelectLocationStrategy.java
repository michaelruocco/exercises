package uk.co.mruoc.exercises.naughtsandcrosses.board;


import java.util.function.Function;

public interface SelectLocationStrategy extends Function<Board, Location> {

    default Location selectLocation(Board board) {
        return apply(board);
    }
}
