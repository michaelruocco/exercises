package uk.co.mruoc.exercises.naughtsandcrosses.game;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.exercises.naughtsandcrosses.Game;
import uk.co.mruoc.exercises.naughtsandcrosses.GameState;
import uk.co.mruoc.exercises.naughtsandcrosses.board.BoardState;
import uk.co.mruoc.exercises.naughtsandcrosses.board.InvalidTurnException;
import uk.co.mruoc.exercises.naughtsandcrosses.locationselector.LocationSelector;

@RequiredArgsConstructor
public class ConsoleGame implements Game {

    private final GameState gameState;
    private final ConsoleBoardFormatter boardFormatter;
    private final LocationSelector locationSelector;

    public ConsoleGame(LocationSelector locationSelector) {
        this(new GameState(), new ConsoleBoardFormatter(), locationSelector);
    }

    @Override
    public void play() {
        System.out.println("game started");
        printBoard();
        while (!gameState.isComplete()) {
            playTurn();
        }
    }

    private void playTurn() {
        String player = gameState.getCurrentPlayer();
        System.out.printf("player %s taking turn%n", player);
        try {
            placeToken(player);
        } catch (InvalidTurnException e) {
            System.out.printf("%s%n%n", e.getMessage());
            return;
        }
        if (gameState.hasWinner(player)) {
            declareWinner(player);
            return;
        }
        if (gameState.isBoardFull()) {
            declareDraw();
            return;
        }
        printBoard();
    }

    private void placeToken(String token) {
        String location = gameState.selectLocation(locationSelector);
        System.out.printf("placing token %s at %s%n", token, location);
        gameState.placeToken(location);
    }

    private void declareWinner(String player) {
        printBoard();
        System.out.printf("player %s wins%n", player);
    }

    private void declareDraw() {
        printBoard();
        System.out.println("game ends a draw");
    }

    private void printBoard() {
        BoardState board = gameState.getBoardState();
        System.out.println(boardFormatter.format(board));
        System.out.println();
    }
}
