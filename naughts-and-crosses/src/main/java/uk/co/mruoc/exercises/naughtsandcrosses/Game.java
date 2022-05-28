package uk.co.mruoc.exercises.naughtsandcrosses;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.exercises.naughtsandcrosses.board.Board;
import uk.co.mruoc.exercises.naughtsandcrosses.board.Location;
import uk.co.mruoc.exercises.naughtsandcrosses.board.SelectLocationStrategy;
import uk.co.mruoc.exercises.naughtsandcrosses.board.SelectUserInputLocationStrategy;

@RequiredArgsConstructor
public class Game {

    private final Players players;
    private final Board board;
    private final SelectLocationStrategy selectLocationStrategy;

    private boolean complete = false;

    public Game() {
        //this(new SelectSpecificLocationStrategy("2-0", "1-0", "1-1", "2-1", "0-2"));
        this(new SelectUserInputLocationStrategy());
    }

    public Game(SelectLocationStrategy selectLocationStrategy) {
        this(new Players(), new Board(), selectLocationStrategy);
    }

    public void play() {
        System.out.println("game started");
        System.out.println();
        while (!complete) {
            playTurn();
        }
    }

    private void playTurn() {
        String player = players.getCurrentPlayer();
        System.out.printf("player %s taking turn%n", player);
        placeToken(player);
        if (board.hasWinner(player)) {
            declareWinner(player);
            return;
        }
        if (board.isFull()) {
            declareDraw();
            return;
        }
        printGridState();
        players.switchPlayer();
    }

    private void placeToken(String token) {
        Location location = board.selectLocation(selectLocationStrategy);
        location.setToken(token);
    }

    private void declareWinner(String player) {
        complete = true;
        printGridState();
        System.out.printf("player %s wins%n", player);
    }

    private void declareDraw() {
        complete = true;
        printGridState();
        System.out.println("game ends a draw");
    }

    private void printGridState() {
        System.out.println(board.getState());
        System.out.println();
    }
}
