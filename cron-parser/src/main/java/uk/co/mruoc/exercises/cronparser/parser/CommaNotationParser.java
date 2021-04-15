package uk.co.mruoc.exercises.cronparser.parser;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import uk.co.mruoc.exercises.cronparser.InvalidValueException;
import uk.co.mruoc.exercises.cronparser.TimeUnit;

import java.util.Arrays;

@Slf4j
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
            throw new InvalidValueException(input, unit);
        }
    }

}
