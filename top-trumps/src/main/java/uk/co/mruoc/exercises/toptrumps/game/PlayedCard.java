package uk.co.mruoc.exercises.toptrumps.game;

import lombok.Builder;
import lombok.Data;
import uk.co.mruoc.exercises.toptrumps.game.card.Card;

@Builder
@Data
public class PlayedCard {

    private final Player player;
    private final Card card;

    public boolean hasAttributeValue(String name, Object value) {
        return card.hasAttributeValue(name, value);
    }
}
