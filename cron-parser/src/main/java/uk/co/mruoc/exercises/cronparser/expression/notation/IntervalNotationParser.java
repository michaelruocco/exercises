package uk.co.mruoc.exercises.cronparser.expression.notation;

import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.cronparser.expression.NotationOutOfBoundsException;
import uk.co.mruoc.exercises.cronparser.expression.TimeUnit;

import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

@Slf4j
public class IntervalNotationParser implements NotationParser {

    private static final String SLASH = "/";

    @Override
    public boolean appliesTo(String value) {
        return value.contains(SLASH);
    }

    @Override
    public int[] toValues(String input, TimeUnit unit) {
        try {
            String[] parts = input.split(SLASH);
            int start = toStart(parts[0], unit);
            unit.validate(start);
            int interval = Integer.parseInt(parts[1]);
            return calculateIntervals(start, unit, interval).toArray();
        } catch (NumberFormatException e) {
            throw new NotationOutOfBoundsException(input, unit);
        }
    }

    private static int toStart(String value, TimeUnit unit) {
        if (value.equals("*")) {
            return unit.getLowerBound();
        }
        return Integer.parseInt(value);
    }

    private static IntStream calculateIntervals(int start, TimeUnit unit, int interval) {
        return IntStream.iterate(start, lessThanUpperBound(unit), incrementBy(interval));
    }

    private static IntPredicate lessThanUpperBound(TimeUnit unit) {
        return i -> i < unit.getUpperBound();
    }

    private static IntUnaryOperator incrementBy(int interval) {
        return i -> i + interval;
    }

}
