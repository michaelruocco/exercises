package uk.co.mruoc.exercises.naughtsandcrosses;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.exercises.naughtsandcrosses.game.Game;
import uk.co.mruoc.exercises.naughtsandcrosses.game.GameFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NaughtsAndCrosses {

    public static void main(String[] args) {
        GameFactory factory = new GameFactory();
        Game game = factory.build(toType(args));
        game.play();
    }

    private static String toType(String[] args) {
        if (args.length > 0) {
            return args[0].toLowerCase();
        }
        return "";
    }
}
