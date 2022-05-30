package uk.co.mruoc.exercises.naughtsandcrosses.game;

import uk.co.mruoc.exercises.naughtsandcrosses.locationselector.SpecificLocationSelector;

public class FixedConsoleGame extends ConsoleGame {

    public FixedConsoleGame() {
        super(new SpecificLocationSelector("3-1", "2-1", "2-2", "3-2", "1-3"));
    }
}
