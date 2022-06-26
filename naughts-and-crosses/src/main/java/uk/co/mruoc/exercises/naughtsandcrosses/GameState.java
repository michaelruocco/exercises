package uk.co.mruoc.exercises.naughtsandcrosses;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.exercises.naughtsandcrosses.board.Board;
import uk.co.mruoc.exercises.naughtsandcrosses.board.BoardState;
import uk.co.mruoc.exercises.naughtsandcrosses.locationselector.LocationSelector;

import java.util.Optional;

@RequiredArgsConstructor
public class GameState {

    private final Players players;
    private final Board board;

    public GameState() {
        this(new Players(), new Board());
    }

    public void reset() {
        board.reset();
    }

    public String getTokenAt(String location) {
        return board.getToken(location);
    }

    public String selectLocation(LocationSelector locationSelector) {
        return board.selectLocation(locationSelector);
    }

    public BoardState getBoardState() {
        return board;
    }

    public boolean isComplete() {
        return hasWinner(players.getCurrentPlayer()) || isBoardFull();
    }

    public Optional<String> getWinner() {
        return players.stream().filter(board::hasWinner).findFirst();
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
            return;
        }
        if (isBoardFull()) {
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
