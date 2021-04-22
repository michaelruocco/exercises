package uk.co.mruoc.exercises.cronparser.expression.notation;

import uk.co.mruoc.exercises.cronparser.expression.TimeUnit;

import java.util.stream.IntStream;

public class RangeNotationParser implements NotationParser {

    private static final String HYPHEN = "-";

    @Override
    public boolean appliesTo(String value) {
        return value.contains(HYPHEN) && value.length() >= 3;
    }

    @Override
    public int[] toValues(String input, TimeUnit unit) {
        try {
            String[] parts = input.split(HYPHEN);
            int start = Integer.parseInt(parts[0]);
            int end = Integer.parseInt(parts[1]);
            unit.validate(start, end);
            return IntStream.rangeClosed(start, end).toArray();
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidNotationException(input, e);
        }
    }

}
