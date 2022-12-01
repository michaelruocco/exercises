package uk.co.mruoc.exercises.toptrumps.game.card;

import lombok.AllArgsConstructor;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

@AllArgsConstructor
public class Deck implements Iterable<Card> {

    private Deque<Card> cards;

    public Deck(Collection<Card> cards) {
        this(new ArrayDeque<>(cards));
    }

    public void shuffle() {
        List<Card> shuffled = new ArrayList<>(cards);
        Collections.shuffle(shuffled);
        cards = new ArrayDeque<>(shuffled);
    }

    public boolean hasCardsRemaining() {
        return !isEmpty();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public Card removeNextCard() {
        return cards.poll();
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }
}
