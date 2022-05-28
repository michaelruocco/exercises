package uk.co.mruoc.exercises.naughtsandcrosses.board;

public class SelectNextFreeLocationStrategy implements SelectLocationStrategy {

    @Override
    public Location apply(Board board) {
        return board.getNextFreeLocation();
    }
}
