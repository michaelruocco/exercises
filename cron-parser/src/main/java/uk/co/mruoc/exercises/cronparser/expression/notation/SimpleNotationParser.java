package uk.co.mruoc.exercises.cronparser.expression.notation;

import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.cronparser.expression.InvalidNotationException;
import uk.co.mruoc.exercises.cronparser.expression.TimeUnit;

import java.util.regex.Pattern;

@Slf4j
public class SimpleNotationParser implements NotationParser {

    private static final Pattern PATTERN = Pattern.compile("\\d+");

    @Override
    public boolean appliesTo(String value) {
        return PATTERN.matcher(value).matches();
    }

    @Override
    public int[] toValues(String input, TimeUnit unit) {
        int value = toInteger(input);
        unit.validate(value);
        return new int[] { value };
    }

    private static int toInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidNotationException(input, e);
        }
    }

}
