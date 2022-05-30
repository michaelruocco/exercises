package uk.co.mruoc.exercises.naughtsandcrosses;

import uk.co.mruoc.exercises.naughtsandcrosses.board.SelectUserInputLocationStrategy;

public class InteractiveConsoleGame extends ConsoleGame {

    public InteractiveConsoleGame() {
        super(new SelectUserInputLocationStrategy());
    }
}
