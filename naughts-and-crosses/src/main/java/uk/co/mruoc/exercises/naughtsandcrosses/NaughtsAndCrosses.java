package uk.co.mruoc.exercises.naughtsandcrosses;

public class NaughtsAndCrosses {

    public static void main(String[] args) {
        Game game = toGame(args);
        game.play();
    }

    private static Game toGame(String[] args) {
        if (shouldPlayInteractiveGame(args)) {
            return new InteractiveGame();
        }
        return new RandomSelectionGame();
    }

    private static boolean shouldPlayInteractiveGame(String[] args) {
        return args.length > 0 && args[0].equals("interactive");
    }
}
