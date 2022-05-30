package uk.co.mruoc.exercises.naughtsandcrosses;

import uk.co.mruoc.exercises.naughtsandcrosses.game.Game;
import uk.co.mruoc.exercises.naughtsandcrosses.game.GameFactory;

public class NaughtsAndCrosses {

    public static void main(String[] args) {
        GameFactory factory = new GameFactory();
        Game game = factory.build(args);
        game.play();
    }
}
