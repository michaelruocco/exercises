package uk.co.mruoc.exercises.naughtsandcrosses.game;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.exercises.naughtsandcrosses.Players;
import uk.co.mruoc.exercises.naughtsandcrosses.board.Board;
import uk.co.mruoc.exercises.naughtsandcrosses.board.InvalidTurnException;
import uk.co.mruoc.exercises.naughtsandcrosses.board.LocationAlreadyTakenException;
import uk.co.mruoc.exercises.naughtsandcrosses.locationselector.LocationSelector;

@RequiredArgsConstructor
public class ConsoleGame implements Game {

    private final Players players;
    private final Board board;
    private final ConsoleBoardFormatter boardFormatter;
    private final LocationSelector locationSelector;

    private boolean complete = false;

    public ConsoleGame(LocationSelector locationSelector) {
        this(new Players(), new Board(), new ConsoleBoardFormatter(), locationSelector);
    }

    @Override
    public void play() {
        System.out.println("game started");
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
            System.out.printf("%s%n%n", e.getMessage());
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
        String location = board.selectLocation(locationSelector);
        if (!board.isFree(location)) {
            throw new LocationAlreadyTakenException(location);
        }
        System.out.printf("placing token %s at %s%n", token, location);
        board.placeToken(location, token);
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