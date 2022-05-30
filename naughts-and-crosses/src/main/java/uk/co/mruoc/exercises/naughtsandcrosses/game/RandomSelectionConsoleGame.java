package uk.co.mruoc.exercises.naughtsandcrosses.game;

import uk.co.mruoc.exercises.naughtsandcrosses.locationselector.RandomLocationSelector;

public class RandomSelectionConsoleGame extends ConsoleGame {

    public RandomSelectionConsoleGame() {
        super(new RandomLocationSelector());
    }
}
