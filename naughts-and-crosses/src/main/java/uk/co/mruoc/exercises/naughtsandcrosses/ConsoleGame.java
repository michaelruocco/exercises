package uk.co.mruoc.exercises.naughtsandcrosses;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.exercises.naughtsandcrosses.board.Board;
import uk.co.mruoc.exercises.naughtsandcrosses.board.ConsoleBoardFormatter;
import uk.co.mruoc.exercises.naughtsandcrosses.board.InvalidTurnException;
import uk.co.mruoc.exercises.naughtsandcrosses.board.Location;
import uk.co.mruoc.exercises.naughtsandcrosses.board.LocationNotFreeException;
import uk.co.mruoc.exercises.naughtsandcrosses.board.SelectLocationStrategy;

@RequiredArgsConstructor
public class ConsoleGame {

    private final Players players;
    private final Board board;
    private final ConsoleBoardFormatter boardFormatter;
    private final SelectLocationStrategy selectLocationStrategy;

    private boolean complete = false;

    public ConsoleGame(SelectLocationStrategy selectLocationStrategy) {
        this(new Players(), new Board(), new ConsoleBoardFormatter(), selectLocationStrategy);
    }

    public void play() {
        System.out.println("game started");
        System.out.println();
        printBoard();
        while (!complete) {
            playTurn();
        }
    }

    private void playTurn() {
        String player = players.getCurrentPlayer();
        System.out.printf("player %s taking turn%n", player);
        try {
            placeToken(player);
        } catch (InvalidTurnException e) {
            System.err.println(e.getMessage());
            return;
        }
        if (board.hasWinner(player)) {
            declareWinner(player);
            return;
        }
        if (board.isFull()) {
            declareDraw();
            return;
        }
        printBoard();
        players.switchPlayer();
    }

    private void placeToken(String token) {
        Location location = board.selectLocation(selectLocationStrategy);
        if (!location.isFree()) {
            throw new LocationNotFreeException(location.getKey());
        }
        location.setToken(token);
    }

    private void declareWinner(String player) {
        complete = true;
        printBoard();
        System.out.printf("player %s wins%n", player);
    }

    private void declareDraw() {
        complete = true;
        printBoard();
        System.out.println("game ends a draw");
    }

    private void printBoard() {
        System.out.println(boardFormatter.format(board));
        System.out.println();
    }
}
