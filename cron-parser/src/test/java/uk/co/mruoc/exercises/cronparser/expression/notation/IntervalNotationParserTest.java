package uk.co.mruoc.exercises.cronparser.expression.notation;

import org.junit.jupiter.api.Test;
import uk.co.mruoc.exercises.cronparser.expression.NotationOutOfBoundsException;
import uk.co.mruoc.exercises.cronparser.expression.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class IntervalNotationParserTest {

    private final NotationParser parser = new IntervalNotationParser();

    @Test
    void shouldOnlyApplyToIntervalAsteriskOrIntegerInput() {
        assertThat(parser.appliesTo("2/2")).isTrue();
        assertThat(parser.appliesTo("*/6")).isTrue();

        assertThat(parser.appliesTo("*/6.5")).isFalse();
        assertThat(parser.appliesTo("2.5/6")).isFalse();
        assertThat(parser.appliesTo("1")).isFalse();
        assertThat(parser.appliesTo("*")).isFalse();
        assertThat(parser.appliesTo("-1")).isFalse();
        assertThat(parser.appliesTo("1-2")).isFalse();
        assertThat(parser.appliesTo("3,4")).isFalse();
        assertThat(parser.appliesTo("text")).isFalse();
    }

    @Test
    void shouldReturnIntervalValues() {
        String input = "2/3";

        int[] values = parser.toValues(input, TimeUnit.HOURS);

        assertThat(values).containsExactly(2, 5, 8, 11, 14, 17, 20, 23);
    }

    @Test
    void shouldThrowExceptionIfInputIsOutsideBoundsOfTimeUnit() {
        String input = "25/2";

        Throwable error = catchThrowable(() -> parser.toValues(input, TimeUnit.HOURS));

        assertThat(error)
                .isInstanceOf(NotationOutOfBoundsException.class)
                .hasMessage("invalid hours value 25, outside bounds 0 and 23");
    }

    @Test
    void shouldThrowExceptionIfInputIsNotIntervalInput() {
        String input = "2";

        Throwable error = catchThrowable(() -> parser.toValues(input, TimeUnit.HOURS));

        assertThat(error)
                .isInstanceOf(InvalidNotationException.class)
                .hasCauseInstanceOf(ArrayIndexOutOfBoundsException.class)
                .hasMessage(input);
    }

    @Test
    void shouldThrowExceptionIfInputIsInBoundsOfTimeUnitButIsNotInteger() {
        String input = "3.5/2";

        Throwable error = catchThrowable(() -> parser.toValues(input, TimeUnit.HOURS));

        assertThat(error)
                .isInstanceOf(InvalidNotationException.class)
                .hasCauseInstanceOf(NumberFormatException.class)
                .hasMessage(input);
    }

}
