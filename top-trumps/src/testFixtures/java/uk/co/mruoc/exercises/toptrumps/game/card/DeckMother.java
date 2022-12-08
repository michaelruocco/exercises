package uk.co.mruoc.exercises.toptrumps.game.card;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeckMother {

    public static Deck withIds(Long... ids) {
        return withIds(List.of(ids));
    }

    public static Deck withIds(Collection<Long> ids) {
        return new Deck(CardMother.withIds(ids));
    }

    public static Deck empty() {
        return new Deck();
    }
}
