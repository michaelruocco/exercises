package uk.co.mruoc.exercises.cronparser.parser;

import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.cronparser.TimeUnit;

@Slf4j
public class SimpleNotationParser implements NotationParser {

    @Override
    public boolean appliesTo(String value) {
        return isInteger(value);
    }

    @Override
    public int[] toValues(String input, TimeUnit unit) {
        int value = toInteger(input);
        unit.validate(value);
        return new int[] { value };
    }

    private static boolean isInteger(String input) {
        return toInteger(input) > -1;
    }

    private static int toInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            log.debug(e.getMessage(), e);
            return -1;
        }
    }

}
