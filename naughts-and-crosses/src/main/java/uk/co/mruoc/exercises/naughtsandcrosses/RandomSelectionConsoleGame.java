package uk.co.mruoc.exercises.naughtsandcrosses;

import uk.co.mruoc.exercises.naughtsandcrosses.board.SelectRandomLocationStrategy;

public class RandomSelectionConsoleGame extends ConsoleGame {

    public RandomSelectionConsoleGame() {
        super(new SelectRandomLocationStrategy());
    }
}
