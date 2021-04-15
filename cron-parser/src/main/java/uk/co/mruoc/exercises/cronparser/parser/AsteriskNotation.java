package uk.co.mruoc.exercises.cronparser.parser;

import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.cronparser.TimeUnit;

@Slf4j
public class AsteriskNotation implements NotationParser {

    @Override
    public boolean appliesTo(String value) {
        return value.equals("*");
    }

    @Override
    public int[] toValues(String input, TimeUnit unit) {
        return unit.allValues();
    }

}
