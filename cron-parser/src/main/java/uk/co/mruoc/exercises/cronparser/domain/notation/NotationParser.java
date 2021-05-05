package uk.co.mruoc.exercises.cronparser.domain.notation;

import uk.co.mruoc.exercises.cronparser.domain.TimeUnit;

public interface NotationParser {

    boolean appliesTo(String value);

    int[] toValues(String input, TimeUnit unit);

}
