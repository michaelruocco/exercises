package uk.co.mruoc.exercises.naughtsandcrosses.console;

import uk.co.mruoc.exercises.naughtsandcrosses.locationselector.RandomLocationSelector;

public class RandomConsoleGame extends ConsoleGame {

    public RandomConsoleGame() {
        super(new RandomLocationSelector());
    }
}
