package uk.co.mruoc.exercises.toptrumps.game.card;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    @Test
    void shouldReturnCardIds() {
        Collection<Long> expectedIds = List.of(4L, 3L, 2L, 1L);
        Deck deck = DeckMother.withIds(expectedIds);

        Collection<Long> ids = deck.toIds();

        assertThat(ids).containsExactlyElementsOf(expectedIds);
    }

    @Test
    void shouldShuffleCardsInDeck() {
        Deck deck = DeckMother.withIds(1L, 2L, 3L, 4L);
        Collection<Long> originalIds = deck.toIds();
        Shuffler shuffler = new IdDescendingShuffler();

        deck.shuffle(shuffler);

        assertThat(deck.toIds()).containsExactlyInAnyOrderElementsOf(originalIds);
    }

    @Test
    void shouldReturnFalseIfNoCardsRemaining() {
        Deck deck = DeckMother.empty();

        boolean cardsRemaining = deck.hasCardsRemaining();

        assertThat(cardsRemaining).isFalse();
    }

    @Test
    void shouldReturnTrueIfCardsRemaining() {
        Deck deck = DeckMother.withIds(1L);

        boolean cardsRemaining = deck.hasCardsRemaining();

        assertThat(cardsRemaining).isTrue();
    }

    @Test
    void shouldReturnFalseIfDeckIsNotEmpty() {
        Deck deck = DeckMother.withIds(1L);

        boolean empty = deck.isEmpty();

        assertThat(empty).isFalse();
    }

    @Test
    void shouldReturnTrueIfDeckIsEmpty() {
        Deck deck = DeckMother.empty();

        boolean empty = deck.isEmpty();

        assertThat(empty).isTrue();
    }

    @Test
    void shouldRemoveTopCardIfDeckIsEmpty() {
        Deck deck = DeckMother.withIds(1L, 2L);

        assertThat(deck.removeTopCard().getId()).isEqualTo(2);
        assertThat(deck.removeTopCard().getId()).isEqualTo(1);
        assertThat(deck.removeTopCard()).isNull();
    }
}
