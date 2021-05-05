package uk.co.mruoc.exercises.cronparser.domain.notation;

import uk.co.mruoc.exercises.cronparser.domain.TimeUnit;


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
