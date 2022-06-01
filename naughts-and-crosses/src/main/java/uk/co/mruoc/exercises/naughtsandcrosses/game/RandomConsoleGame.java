package uk.co.mruoc.exercises.naughtsandcrosses.game;

import uk.co.mruoc.exercises.naughtsandcrosses.locationselector.RandomLocationSelector;

public class RandomConsoleGame extends ConsoleGame {

    public RandomConsoleGame() {
        super(new RandomLocationSelector());
    }
}
