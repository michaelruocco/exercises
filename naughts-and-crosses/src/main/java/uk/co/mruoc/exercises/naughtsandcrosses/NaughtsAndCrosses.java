package uk.co.mruoc.exercises.naughtsandcrosses;

public class NaughtsAndCrosses {

    public static void main(String[] args) {
        ConsoleGame game = toGame(args);
        game.play();
    }

    private static ConsoleGame toGame(String[] args) {
        if (shouldPlayInteractiveGame(args)) {
            return new InteractiveConsoleGame();
        }
        if (shouldPlayFixedGame(args)) {
            return new FixedConsoleGame();
        }
        return new RandomSelectionConsoleGame();
    }

    private static boolean shouldPlayInteractiveGame(String[] args) {
        return shouldPlayGameType(args,"interactive");
    }

    private static boolean shouldPlayFixedGame(String[] args) {
        return shouldPlayGameType(args,"fixed");
    }

    private static boolean shouldPlayGameType(String[] args, String type) {
        return args.length > 0 && args[0].equals(type);
    }
}
