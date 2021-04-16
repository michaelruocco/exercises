package uk.co.mruoc.exercises.cronparser.expression.notation;

import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.cronparser.expression.TimeUnit;

@Slf4j
public class AsteriskNotationParser implements NotationParser {

    @Override
    public boolean appliesTo(String value) {
        return value.equals("*");
    }

    @Override
    public int[] toValues(String input, TimeUnit unit) {
        return unit.allValues();
    }

}
