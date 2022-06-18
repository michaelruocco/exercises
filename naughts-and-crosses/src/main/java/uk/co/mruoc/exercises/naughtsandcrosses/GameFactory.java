package uk.co.mruoc.exercises.naughtsandcrosses;

import uk.co.mruoc.exercises.naughtsandcrosses.console.ConsoleGame;
import uk.co.mruoc.exercises.naughtsandcrosses.console.DrawConsoleGame;
import uk.co.mruoc.exercises.naughtsandcrosses.console.FixedConsoleGame;
import uk.co.mruoc.exercises.naughtsandcrosses.console.InteractiveConsoleGame;
import uk.co.mruoc.exercises.naughtsandcrosses.console.RandomConsoleGame;

public class GameFactory {

    public ConsoleGame build(String type) {
        if (isInteractive(type)) {
            return new InteractiveConsoleGame();
        }
        if (isFixed(type)) {
            return new FixedConsoleGame();
        }
        if (isDraw(type)) {
            return new DrawConsoleGame();
        }
        return new RandomConsoleGame();
    }

    private static boolean isInteractive(String type) {
        return "interactive".equals(type);
    }

    private static boolean isFixed(String type) {
        return "fixed".equals(type);
    }

    private static boolean isDraw(String type) {
        return "draw".equals(type);
    }
}
