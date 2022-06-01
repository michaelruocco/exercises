package uk.co.mruoc.exercises.naughtsandcrosses.game;

public class GameFactory {

    public ConsoleGame build(String type) {
        if (isInteractive(type)) {
            return new InteractiveConsoleGame();
        }
        if (isFixed(type)) {
            return new FixedConsoleGame();
        }
        return new RandomConsoleGame();
    }

    private static boolean isInteractive(String type) {
        return "interactive".equals(type);
    }

    private static boolean isFixed(String type) {
        return "fixed".equals(type);
    }
}
