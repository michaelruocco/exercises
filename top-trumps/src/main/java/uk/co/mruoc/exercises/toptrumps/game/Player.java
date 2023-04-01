package uk.co.mruoc.exercises.toptrumps.game;

import lombok.AllArgsConstructor;
import uk.co.mruoc.exercises.toptrumps.game.card.Card;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

import static uk.co.mruoc.exercises.toptrumps.game.card.Card.toFormattedCardIds;

@AllArgsConstructor
public class Player {

    private final String name;
    private final Deque<Card> cards;

    public Player(String name) {
        this(name, new ArrayDeque<>());
    }

    public String toString() {
        return String.format("name:%s numberOfCards:%d cardIds:(%s)", name, cards.size(), toFormattedCardIds(cards));
    }

    public String getName() {
        return name;
    }

    public boolean hasCards() {
        return !cards.isEmpty();
    }

    public Card getNextCard() {
        return cards.peek();
    }

    public Card removeNextCard() {
        return cards.poll();
    }

    public void addCards(Collection<Card> cards) {
        this.cards.addAll(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }
}
