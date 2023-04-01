package uk.co.mruoc.exercises.toptrumps.game.card;

import lombok.RequiredArgsConstructor;

import java.util.Comparator;

@RequiredArgsConstructor
public class IdDescendingShuffler implements Shuffler {

    private final Comparator<Card> cardComparator;

    public IdDescendingShuffler() {
        this(Comparator.comparingLong(Card::getId).reversed());
    }

    @Override
    public int compare(Card c1, Card c2) {
        return cardComparator.compare(c1, c2);
    }
}
