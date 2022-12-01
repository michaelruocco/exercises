package uk.co.mruoc.exercises.toptrumps;

import uk.co.mruoc.exercises.toptrumps.category.footballer.FootballerDeckLoader;
import uk.co.mruoc.exercises.toptrumps.game.Game;
import uk.co.mruoc.exercises.toptrumps.game.Player;
import uk.co.mruoc.exercises.toptrumps.game.Players;
import uk.co.mruoc.exercises.toptrumps.game.card.Pile;

public class Main {

    public static void main(String[] args) {
        Game game = Game.builder()
                .deckLoader(new FootballerDeckLoader())
                //.players(new Players(new Player("Player 1"), new Player("Player 2"), new Player("Player 3")))
                .players(new Players(new Player("Player 1"), new Player("Player 2")))
                .pile(new Pile())
                //.maxTurns(10)
                .maxTurns(Integer.MAX_VALUE)
                .build();
        game.play();
    }
}
