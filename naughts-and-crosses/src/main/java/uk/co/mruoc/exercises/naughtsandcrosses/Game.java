package uk.co.mruoc.exercises.naughtsandcrosses;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Game {

    private final Players players;
    private final Board board;

    public Game() {
        this(new Players(), new Board());
    }

    public void play() {
        System.out.println("game started");
        System.out.println();
        while (board.hasLocationsRemaining()) {
            playTurn();
        }
        System.out.println("game complete - no locations remaining");
    }

    private void playTurn() {
        String player = players.getCurrentPlayer();
        System.out.printf("player %s taking turn%n", player);
        board.placeToken(player);
        players.switchPlayer();
        printGridState();
    }

    private void printGridState() {
        System.out.println(board.getState());
        System.out.println();
    }

}
