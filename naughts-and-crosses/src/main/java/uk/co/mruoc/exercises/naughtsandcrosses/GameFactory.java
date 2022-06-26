package uk.co.mruoc.exercises.naughtsandcrosses;

import uk.co.mruoc.exercises.naughtsandcrosses.console.DrawConsoleGame;
import uk.co.mruoc.exercises.naughtsandcrosses.console.FixedConsoleGame;
import uk.co.mruoc.exercises.naughtsandcrosses.console.InteractiveConsoleGame;
import uk.co.mruoc.exercises.naughtsandcrosses.console.RandomConsoleGame;
import uk.co.mruoc.exercises.naughtsandcrosses.gui.GuiGame;

public class GameFactory {

    public Game build(String type) {
        if (isInteractive(type)) {
            return new InteractiveConsoleGame();
        }
        if (isFixed(type)) {
            return new FixedConsoleGame();
        }
        if (isDraw(type)) {
            return new DrawConsoleGame();
        }
        if (isGui(type)) {
            return new GuiGame();
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

    private static boolean isGui(String type) {
        return "gui".equals(type);
    }
}
