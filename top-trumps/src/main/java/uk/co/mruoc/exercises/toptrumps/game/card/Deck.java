package uk.co.mruoc.exercises.toptrumps.game.card;

import lombok.AllArgsConstructor;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class Deck {

    private Deque<Card> cards;

    public Deck(Card... cards) {
        this(List.of(cards));
    }

    public Deck(Collection<Card> cards) {
        this(new ArrayDeque<>(cards));
    }

    public void shuffle(Comparator<Card> comparator) {
        cards = cards.stream()
                .sorted(comparator)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    public boolean hasCardsRemaining() {
        return !isEmpty();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public Card removeTopCard() {
        return cards.pollLast();
    }

    public Collection<Long> toIds() {
        return cards.stream().map(Card::getId).toList();
    }
}
