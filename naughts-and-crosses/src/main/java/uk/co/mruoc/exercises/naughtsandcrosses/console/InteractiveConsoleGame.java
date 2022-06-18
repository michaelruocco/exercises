package uk.co.mruoc.exercises.naughtsandcrosses.console;

import uk.co.mruoc.exercises.naughtsandcrosses.locationselector.UserInputLocationSelector;

public class InteractiveConsoleGame extends ConsoleGame {

    public InteractiveConsoleGame() {
        super(new UserInputLocationSelector());
    }
}
