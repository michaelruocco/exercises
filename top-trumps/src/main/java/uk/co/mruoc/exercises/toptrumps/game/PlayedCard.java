package uk.co.mruoc.exercises.toptrumps.game;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.exercises.toptrumps.game.card.Card;

import java.util.Optional;

@RequiredArgsConstructor
@Data
public class PlayedCard {

    private final Player player;
    private final Card card;

    public String getPlayerName() {
        return player.getName();
    }

    public long getCardId() {
        return card.getId();
    }

    public Optional<PlayedCard> calculateWinner(PlayedCard otherPlayedCard, String attributeName) {
        return switch (this.card.calculateResult(otherPlayedCard.getCard(), attributeName)) {
            case WIN -> Optional.of(this);
            case LOSE -> Optional.of(otherPlayedCard);
            case DRAW -> Optional.empty();
        };
    }
}
