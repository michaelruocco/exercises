package uk.co.mruoc.exercises.cronparser.expression.notation;

import uk.co.mruoc.exercises.cronparser.expression.TimeUnit;

public interface NotationParser {

    boolean appliesTo(String value);

    int[] toValues(String input, TimeUnit unit);

}
