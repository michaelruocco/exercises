package uk.co.mruoc.exercises.cronparser.expression.notation;

import uk.co.mruoc.exercises.cronparser.expression.TimeUnit;

import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

public class IntervalNotationParser implements NotationParser {

    private static final String FORWARD_SLASH = "/";

    @Override
    public boolean appliesTo(String value) {
        return value.contains(FORWARD_SLASH);
    }

    @Override
    public int[] toValues(String input, TimeUnit unit) {
        try {
            String[] parts = input.split(FORWARD_SLASH);
            int start = toStart(parts[0], unit);
            unit.validate(start);
            int interval = Integer.parseInt(parts[1]);
            return calculateIntervals(start, unit, interval);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidNotationException(input, e);
        }
    }

    private static int toStart(String value, TimeUnit unit) {
        if (value.equals("*")) {
            return unit.getLowerBound();
        }
        return Integer.parseInt(value);
    }

    private static int[] calculateIntervals(int start, TimeUnit unit, int interval) {
        return IntStream.iterate(start, lessThanOrEqualToUpperBound(unit), incrementBy(interval))
                .toArray();
    }

    private static IntPredicate lessThanOrEqualToUpperBound(TimeUnit unit) {
        return i -> i <= unit.getUpperBound();
    }

    private static IntUnaryOperator incrementBy(int interval) {
        return i -> i + interval;
    }

}
