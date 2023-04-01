package uk.co.mruoc.exercises.toptrumps;

import uk.co.mruoc.exercises.toptrumps.category.footballer.FootballerDeckLoader;
import uk.co.mruoc.exercises.toptrumps.game.Game;
import uk.co.mruoc.exercises.toptrumps.game.Player;
import uk.co.mruoc.exercises.toptrumps.game.Players;
import uk.co.mruoc.exercises.toptrumps.game.card.Pile;
import uk.co.mruoc.exercises.toptrumps.game.card.RandomShuffler;
import uk.co.mruoc.exercises.toptrumps.game.card.attribute.RandomAttributeSelector;

public class Main {

    public static void main(String[] args) {
        Game game = Game.builder()
                .pile(new Pile())
                .maxTurns(Integer.MAX_VALUE)
                .deck(new FootballerDeckLoader().load())
                .shuffler(new RandomShuffler())
                .attributeSelector(new RandomAttributeSelector())
                .players(new Players(new Player("Player 1"), new Player("Player 2"), new Player("Player 3")))
                .build();
        game.play();
    }
}
