package uk.co.mruoc.exercises.toptrumps.game.card.attribute;

import uk.co.mruoc.exercises.toptrumps.game.card.Result;

public interface Attribute {

    String getName();

    Object getValue();

    Result calculateResult(Attribute otherAttribute);

    default boolean hasName(String name) {
        return getName().equals(name);
    }

    default <T> T getValueAs(Class<T> type) {
        Object value = getValue();
        if (type.isAssignableFrom(value.getClass())) {
            return type.cast(value);
        }
        throw new InvalidAttributeTypeException(getName(), value, type);
    }
}
