package uk.co.mruoc.exercises.cronparser.parser;

import uk.co.mruoc.exercises.cronparser.TimeUnit;

public interface NotationParser {

    boolean appliesTo(String value);

    int[] toValues(String input, TimeUnit unit);

}
