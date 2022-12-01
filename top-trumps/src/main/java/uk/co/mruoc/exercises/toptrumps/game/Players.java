package uk.co.mruoc.exercises.toptrumps.game;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import uk.co.mruoc.exercises.toptrumps.game.card.Card;
import uk.co.mruoc.exercises.toptrumps.game.card.Deck;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@ToString
public class Players implements Iterable<Player> {

    private final List<Player> values;
    private final int nextTurnIndex;

    public Players(Player... values) {
        this(List.of(values), 0);
    }

    public boolean allOutOfCards() {
        return values.stream().noneMatch(Player::hasCards);
    }

    public Optional<Player> getWinner() {
        if (onlyOnePlayerRemaining()) {
            return getRemainingPlayers().stream().findFirst();
        }
        return Optional.empty();
    }

    public boolean onlyOnePlayerRemaining() {
        return getRemainingPlayers().size() == 1;
    }

    public void deal(Deck deck) {
        int dealIndex = 0;
        while(deck.hasCardsRemaining()) {
            Player player = values.get(dealIndex);
            player.addCard(deck.removeNextCard());
            dealIndex++;
            if (dealIndex >= values.size()) {
                dealIndex = 0;
            }
        }
    }

    @Override
    public Iterator<Player> iterator() {
        return values.iterator();
    }

    public Collection<Card> getNextCards() {
        return values.stream()
                .filter(Player::hasCards)
                .map(Player::getNextCard)
                .toList();
    }

    public List<PlayedCard> removeNextPlayedCards() {
        return values.stream()
                .filter(Player::hasCards)
                .map(player -> new PlayedCard(player, player.removeNextCard()))
                .toList();
    }

    private Collection<Player> getRemainingPlayers() {
        return values.stream().filter(Player::hasCards).toList();
    }
}
