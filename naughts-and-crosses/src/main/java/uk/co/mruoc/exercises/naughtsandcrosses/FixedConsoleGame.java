package uk.co.mruoc.exercises.naughtsandcrosses;

import uk.co.mruoc.exercises.naughtsandcrosses.board.SelectSpecificLocationStrategy;

public class FixedConsoleGame extends ConsoleGame {

    public FixedConsoleGame() {
        super(new SelectSpecificLocationStrategy("3-1", "2-1", "2-2", "3-2", "1-3"));
    }
}
