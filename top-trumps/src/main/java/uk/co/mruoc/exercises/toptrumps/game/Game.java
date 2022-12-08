package uk.co.mruoc.exercises.toptrumps.game;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.toptrumps.game.card.Card;
import uk.co.mruoc.exercises.toptrumps.game.card.Deck;
import uk.co.mruoc.exercises.toptrumps.game.card.Pile;
import uk.co.mruoc.exercises.toptrumps.game.card.Shuffler;
import uk.co.mruoc.exercises.toptrumps.game.card.attribute.Attribute;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Builder
public class Game {

    private final Deck deck;
    private final Shuffler shuffler;
    private final Players players;
    private final Pile pile;
    private final int maxTurns;

    public void play() {
        log.info("starting game");
        shuffle();
        deal();
        int turns = 0;
        while (!isFinished() && turns <= maxTurns) {
            takeTurn(selectRandomAttribute());
            log.info("turn {} complete ", turns++);
        }
        log.info("winner is {}", getWinner());
    }

    public void shuffle() {
        deck.shuffle(shuffler);
    }

    public void deal() {
        players.deal(deck);
    }

    public boolean isFinished() {
        return players.allOutOfCards() || players.onlyOnePlayerRemaining();
    }

    public Optional<Player> getWinner() {
        return players.getWinner();
    }

    public Attribute selectRandomAttribute() {
        return selectRandom(getAvailableAttributes());
    }

    public Collection<Attribute> getAvailableAttributes() {
        return players.getNextCards()
                .stream()
                .findFirst()
                .map(Card::getAttributes)
                .orElse(Collections.emptyList());
    }

    public void takeTurn(Attribute selectedAttribute) {
        Collection<PlayedCard> playedCards = players.removeNextPlayedCards();
        pile.addAll(playedCards);
        Optional<Player> turnWinner = selectedAttribute.calculateWinner(playedCards);
        turnWinner.ifPresent(player -> player.addCards(pile.removeAll()));
        players.log();
    }

    private static Attribute selectRandom(Collection<Attribute> attributes) {
        List<Attribute> randomized = new ArrayList<>(attributes);
        Collections.shuffle(randomized);
        Attribute attribute = randomized.get(0);
        log.info("selected random attribute {}", attribute.getName());
        return attribute;
    }
}
