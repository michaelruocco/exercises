package uk.co.mruoc.exercises.toptrumps.game.card.attribute;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.toptrumps.game.PlayedCard;
import uk.co.mruoc.exercises.toptrumps.game.Player;

import java.util.Collection;
import java.util.Optional;
import java.util.OptionalDouble;

@Slf4j
@RequiredArgsConstructor
@Data
public class NumericHighestWinsAttribute implements Attribute {

    private final String name;
    private final Double value;

    @Override
    public Optional<Player> calculateWinner(Collection<PlayedCard> playedCards) {
        OptionalDouble highestValue = findMaxValue(playedCards);
        if (highestValue.isEmpty()) {
            return Optional.empty();
        }
        Collection<PlayedCard> highestValueCards = findCardsWithValue(playedCards, highestValue.getAsDouble());
        if (highestValueCards.size() == 1) {
            return highestValueCards.stream().findFirst().map(PlayedCard::getPlayer);
        }
        return Optional.empty();
    }

    private OptionalDouble findMaxValue(Collection<PlayedCard> playedCards) {
        return playedCards.stream()
                .map(PlayedCard::getCard)
                .map(card -> card.getAttributeByName(name))
                .mapToDouble(attribute -> attribute.getValueAs(Double.class))
                .max();
    }

    private Collection<PlayedCard> findCardsWithValue(Collection<PlayedCard> playedCards, Object value) {
        return playedCards.stream()
                .filter(playedCard -> playedCard.hasAttributeValue(name, value))
                .toList();
    }
}
