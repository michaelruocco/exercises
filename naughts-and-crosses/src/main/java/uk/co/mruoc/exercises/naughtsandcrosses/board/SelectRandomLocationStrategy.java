package uk.co.mruoc.exercises.naughtsandcrosses.board;

public class SelectRandomLocationStrategy implements SelectLocationStrategy {

    @Override
    public Location apply(Board board) {
        return board.getRandomFreeLocation();
    }
}
