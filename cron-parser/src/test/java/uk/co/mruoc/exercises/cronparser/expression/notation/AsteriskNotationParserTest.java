package uk.co.mruoc.exercises.cronparser.expression.notation;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.exercises.cronparser.expression.TimeUnit.DAYS_OF_MONTH;
import static uk.co.mruoc.exercises.cronparser.expression.TimeUnit.DAYS_OF_WEEK;
import static uk.co.mruoc.exercises.cronparser.expression.TimeUnit.HOURS;
import static uk.co.mruoc.exercises.cronparser.expression.TimeUnit.MINUTES;
import static uk.co.mruoc.exercises.cronparser.expression.TimeUnit.MONTHS;

class AsteriskNotationParserTest {

    private final NotationParser parser = new AsteriskNotationParser();

    @Test
    void shouldOnlyApplyToAsterisk() {
        assertThat(parser.appliesTo("*")).isTrue();

        assertThat(parser.appliesTo("1")).isFalse();
        assertThat(parser.appliesTo("-1")).isFalse();
        assertThat(parser.appliesTo("*/2")).isFalse();
        assertThat(parser.appliesTo("3,4")).isFalse();
        assertThat(parser.appliesTo("5-6")).isFalse();
        assertThat(parser.appliesTo("text")).isFalse();
    }

    @Test
    void shouldReturnAllValuesFromTimeUnitRegardlessOfInput() {
        String anyInput = "any";

        assertThat(parser.toValues(anyInput, MINUTES)).containsExactly(allValuesInclusive(0, 59));
        assertThat(parser.toValues(anyInput, HOURS)).containsExactly(allValuesInclusive(0, 23));
        assertThat(parser.toValues(anyInput, DAYS_OF_WEEK)).containsExactly(allValuesInclusive(0, 6));
        assertThat(parser.toValues(anyInput, DAYS_OF_MONTH)).containsExactly(allValuesInclusive(1, 31));
        assertThat(parser.toValues(anyInput, MONTHS)).containsExactly(allValuesInclusive(1, 12));
    }

    private static Integer[] allValuesInclusive(int start, int end) {
        return IntStream.rangeClosed(start, end).boxed().toArray(Integer[]::new);
    }

}
