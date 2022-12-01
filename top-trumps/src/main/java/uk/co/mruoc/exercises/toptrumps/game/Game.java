package uk.co.mruoc.exercises.toptrumps.game;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.toptrumps.game.card.Card;
import uk.co.mruoc.exercises.toptrumps.game.card.Deck;
import uk.co.mruoc.exercises.toptrumps.game.card.DeckLoader;
import uk.co.mruoc.exercises.toptrumps.game.card.Pile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Builder
public class Game {

    private final DeckLoader deckLoader;
    private final Players players;
    private final Pile pile;
    private final int maxTurns;

    public void play() {
        log.info("starting game");
        logPlayers();
        deal();
        int turns = 0;
        while (!isFinished() && turns <= maxTurns) {
            Collection<String> attributes = getAvailableAttributes();
            String attribute = selectRandom(attributes);
            takeTurn(attribute);
            log.info("turn {} complete ", turns++);
            logPlayers();
        }
        log.info("winner is {}", getWinner());
    }

    public void deal() {
        Deck deck = deckLoader.load();
        deck.shuffle();
        players.deal(deck);

        log.info("cards dealt");
        logPlayers();
    }

    public boolean isFinished() {
        return players.allOutOfCards() || players.onlyOnePlayerRemaining();
    }

    public Optional<Player> getWinner() {
        return players.getWinner();
    }

    public Collection<String> getAvailableAttributes() {
        return players.getNextCards()
                .stream()
                .findFirst()
                .map(Card::getAttributeNames)
                .orElse(Collections.emptyList());
    }

    public void takeTurn(String attributeName) {
        List<PlayedCard> playedCards = players.removeNextPlayedCards();
        pile.addAll(playedCards);
        Optional<Player> turnWinner = calculateTurnWinner(playedCards, attributeName);
        turnWinner.ifPresent(player -> player.addCards(pile.removeAll()));
    }

    //TODO tidy up this method or create turn class which might help break down
    private Optional<Player> calculateTurnWinner(List<PlayedCard> playedCards, String attributeName) {
        Optional<PlayedCard> turnWinner = Optional.empty();
        for (int i = 0; i + 1 < playedCards.size(); i++) {
            PlayedCard playedCard1 = playedCards.get(i);
            PlayedCard playedCard2 = playedCards.get(i + 1);
            Optional<PlayedCard> cardWinner = playedCard1.calculateWinner(playedCard2, attributeName);
            if (cardWinner.isPresent() && turnWinner.isPresent() && !turnWinner.get().getPlayerName().equals(cardWinner.get().getPlayerName())) {
                turnWinner = turnWinner.get().calculateWinner(cardWinner.get(), attributeName);
            } else if (cardWinner.isPresent()) {
                turnWinner = cardWinner;
            }
        }
        turnWinner.ifPresent(playedCard -> log.info("winning card {} from {}",
                playedCard.getCardId(),
                playedCard.getPlayer().getName()));
        return turnWinner.map(PlayedCard::getPlayer);
    }

    private void logPlayers() {
        players.forEach(player -> log.info(player.toString()));
    }

    private static String selectRandom(Collection<String> attributes) {
        List<String> randomized = new ArrayList<>(attributes);
        Collections.shuffle(randomized);
        String attribute = randomized.get(0);
        log.info("selected random attribute {}", attribute);
        return attribute;
    }
}
