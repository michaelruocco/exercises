package uk.co.mruoc.exercises.cronparser.expression.notation;

import org.apache.commons.lang3.StringUtils;
import uk.co.mruoc.exercises.cronparser.expression.TimeUnit;

import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

import static uk.co.mruoc.exercises.cronparser.expression.notation.StringUtil.isInt;

public class IntervalNotationParser implements NotationParser {

    private static final String WILDCARD = "*";

    @Override
    public boolean appliesTo(String value) {
        String[] parts = split(value);
        if (parts.length == 2) {
            return isIntOrWildcard(parts[0]) && isInt(parts[1]);
        }
        return false;
    }

    @Override
    public int[] toValues(String input, TimeUnit unit) {
        try {
            String[] parts = split(input);
            int start = toStart(parts[0], unit);
            unit.validate(start);
            var interval = Integer.parseInt(parts[1]);
            return calculateIntervals(start, unit, interval);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidNotationException(input, e);
        }
    }

    private static String[] split(String value) {
        return StringUtils.split(value, "/");
    }

    private static int toStart(String value, TimeUnit unit) {
        if (WILDCARD.equals(value)) {
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

    private static boolean isIntOrWildcard(String value) {
        return isInt(value) || WILDCARD.equals(value);
    }

}
