package uk.co.mruoc.exercises.naughtsandcrosses.game;

public class GameFactory {

    public ConsoleGame build(String[] args) {
        if (containsInteractive(args)) {
            return new InteractiveConsoleGame();
        }
        if (containsFixed(args)) {
            return new FixedConsoleGame();
        }
        return new RandomSelectionConsoleGame();
    }

    private static boolean containsInteractive(String[] args) {
        return containsType(args,"interactive");
    }

    private static boolean containsFixed(String[] args) {
        return containsType(args,"fixed");
    }

    private static boolean containsType(String[] args, String type) {
        return args.length > 0 && args[0].equals(type);
    }
}
