package uk.co.mruoc.exercises.cronparser.expression.notation;

import uk.co.mruoc.exercises.cronparser.expression.TimeUnit;


public class WildcardNotationParser implements NotationParser {

    @Override
    public boolean appliesTo(String value) {
        return value.equals("*");
    }

    @Override
    public int[] toValues(String input, TimeUnit unit) {
        return unit.allValues();
    }

}
