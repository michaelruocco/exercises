package uk.co.mruoc.exercises.cronparser.expression.notation;

import uk.co.mruoc.exercises.cronparser.expression.TimeUnit;

import static uk.co.mruoc.exercises.cronparser.expression.notation.StringUtil.isInt;

public class SimpleNotationParser implements NotationParser {

    @Override
    public boolean appliesTo(String value) {
        return isInt(value);
    }

    @Override
    public int[] toValues(String input, TimeUnit unit) {
        var value = toInteger(input);
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
