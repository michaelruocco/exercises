package uk.co.mruoc.exercises.naughtsandcrosses;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.exercises.naughtsandcrosses.board.Board;
import uk.co.mruoc.exercises.naughtsandcrosses.board.BoardState;
import uk.co.mruoc.exercises.naughtsandcrosses.locationselector.LocationSelector;

@RequiredArgsConstructor
public class GameState {

    private final Players players;
    private final Board board;

    private boolean complete = false;

    public GameState() {
        this(new Players(), new Board());
    }

    public String selectLocation(LocationSelector locationSelector) {
        return board.selectLocation(locationSelector);
    }

    public BoardState getBoardState() {
        return board;
    }

    public boolean isComplete() {
        return complete;
    }

    public boolean hasWinner(String player) {
        return board.hasWinner(player);
    }

    public boolean isBoardFull() {
        return board.isFull();
    }

    public void placeToken(String location) {
        String player = getCurrentPlayer();
        board.placeToken(location, player);
        if (hasWinner(player)) {
            complete = true;
            return;
        }
        if (isBoardFull()) {
            complete = true;
            return;
        }
        switchPlayer();
    }

    public String getCurrentPlayer() {
        return players.getCurrentPlayer();
    }

    public void switchPlayer() {
        players.switchPlayer();
    }
}
