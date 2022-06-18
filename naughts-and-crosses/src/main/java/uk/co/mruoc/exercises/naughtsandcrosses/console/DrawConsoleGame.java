package uk.co.mruoc.exercises.naughtsandcrosses.console;

import uk.co.mruoc.exercises.naughtsandcrosses.locationselector.SpecificLocationSelector;

public class DrawConsoleGame extends ConsoleGame {

    public DrawConsoleGame() {
        super(new SpecificLocationSelector(
                "1-1", "2-1", "1-2",
                "1-3", "2-2", "3-2",
                "2-3", "3-3", "3-1"));
    }
}
