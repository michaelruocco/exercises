package uk.co.mruoc.exercises.toptrumps.game.card;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.toptrumps.game.PlayedCard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static uk.co.mruoc.exercises.toptrumps.game.card.Card.toFormattedCardIds;

@Slf4j
@AllArgsConstructor
public class Pile implements Iterable<Card> {

    private Collection<Card> cards;

    public Pile() {
        this(new ArrayList<>());
    }

    public void addAll(Collection<PlayedCard> playedCards) {
        this.cards.addAll(playedCards.stream().map(PlayedCard::getCard).toList());
    }

    public Collection<Card> removeAll() {
        Collection<Card> cardsToReturn = new ArrayList<>(cards);
        cards.clear();
        log.info("removing cards from pile {}", toFormattedCardIds(cardsToReturn));
        return cardsToReturn;
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }
}
