package uk.co.mruoc.exercises.toptrumps.game.card.attribute;

import uk.co.mruoc.exercises.toptrumps.game.PlayedCard;
import uk.co.mruoc.exercises.toptrumps.game.Player;

import java.util.Collection;
import java.util.Optional;

public interface Attribute {

    String getName();

    Object getValue();

    Optional<Player> calculateWinner(Collection<PlayedCard> playedCards);

    default boolean hasName(String name) {
        return getName().equals(name);
    }

    default boolean hasValue(Object value) { return getValue().equals(value); }

    default <T> T getValueAs(Class<T> type) {
        Object value = getValue();
        if (type.isAssignableFrom(value.getClass())) {
            return type.cast(value);
        }
        throw new InvalidAttributeTypeException(getName(), value, type);
    }
}
