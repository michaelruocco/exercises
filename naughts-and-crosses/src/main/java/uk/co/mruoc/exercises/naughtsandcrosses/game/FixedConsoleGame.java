package uk.co.mruoc.exercises.naughtsandcrosses.game;

import uk.co.mruoc.exercises.naughtsandcrosses.locationselector.NextFreeLocationSelector;

public class FixedConsoleGame extends ConsoleGame {

    public FixedConsoleGame() {
        super(new NextFreeLocationSelector());
    }
}
