package uk.co.mruoc.exercises.cronparser.expression.notation;

import org.apache.commons.lang3.StringUtils;
import uk.co.mruoc.exercises.cronparser.expression.TimeUnit;

import java.util.stream.IntStream;

import static uk.co.mruoc.exercises.cronparser.expression.notation.StringUtil.isInt;

public class RangeNotationParser implements NotationParser {

    @Override
    public boolean appliesTo(String value) {
        String[] parts = split(value);
        if (parts.length == 2) {
            return isInt(parts[0]) && isInt(parts[1]);
        }
        return false;
    }

    @Override
    public int[] toValues(String input, TimeUnit unit) {
        try {
            String[] parts = split(input);
            var start = Integer.parseInt(parts[0]);
            var end = Integer.parseInt(parts[1]);
            unit.validate(start, end);
            return IntStream.rangeClosed(start, end).toArray();
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidNotationException(input, e);
        }
    }

    private static String[] split(String value) {
        return StringUtils.split(value, "-");
    }

}
