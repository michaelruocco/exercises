package uk.co.mruoc.exercises.toptrumps.game.card.attribute;

public class InvalidAttributeTypeException extends RuntimeException {

    public InvalidAttributeTypeException(String name, Object value, Class<?> type) {
        super(toMessage(name, value, type));
    }

    private static String toMessage(String name, Object value, Class<?> type) {
        return String.format("invalid type %s for attribute %s with value %s",
                type,
                name,
                value);
    }
}
