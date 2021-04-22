package uk.co.mruoc.exercises.cronparser.expression.notation;

import org.apache.commons.lang3.StringUtils;
import uk.co.mruoc.exercises.cronparser.expression.TimeUnit;

import java.util.Arrays;

public class CommaNotationParser implements NotationParser {

    private static final String COMMA = ",";

    @Override
    public boolean appliesTo(String value) {
        return value.contains(COMMA);
    }

    @Override
    public int[] toValues(String input, TimeUnit unit) {
        return Arrays.stream(StringUtils.split(input, COMMA))
                .mapToInt(value -> toInt(value, unit))
                .toArray();
    }

    private static int toInt(String input, TimeUnit unit) {
        try {
            int value = Integer.parseInt(input);
            unit.validate(value);
            return value;
        } catch (NumberFormatException e) {
            throw new InvalidNotationException(input, e);
        }
    }

}
