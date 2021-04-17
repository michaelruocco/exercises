package uk.co.mruoc.exercises.cronparser.expression.notation;

import org.junit.jupiter.api.Test;
import uk.co.mruoc.exercises.cronparser.expression.InvalidNotationException;
import uk.co.mruoc.exercises.cronparser.expression.NotationOutOfBoundsException;
import uk.co.mruoc.exercises.cronparser.expression.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class CommaNotationParserTest {

    private final NotationParser parser = new CommaNotationParser();

    @Test
    void shouldOnlyApplyToNumericRangeInput() {
        assertThat(parser.appliesTo("5,6")).isTrue();

        assertThat(parser.appliesTo("1")).isFalse();
        assertThat(parser.appliesTo("*")).isFalse();
        assertThat(parser.appliesTo("-1")).isFalse();
        assertThat(parser.appliesTo("*/2")).isFalse();
        assertThat(parser.appliesTo("3-4")).isFalse();
        assertThat(parser.appliesTo("text")).isFalse();
    }

    @Test
    void shouldReturnSingleValue() {
        String input = "4";

        int[] values = parser.toValues(input, TimeUnit.HOURS);

        assertThat(values).containsExactly(4);
    }

    @Test
    void shouldReturnSpecifiedValues() {
        String input = "1,3,5";

        int[] values = parser.toValues(input, TimeUnit.HOURS);

        assertThat(values).containsExactly(1, 3, 5);
    }

    @Test
    void shouldThrowExceptionIfInputIsOutsideBoundsOfTimeUnit() {
        String input = "1,25";

        Throwable error = catchThrowable(() -> parser.toValues(input, TimeUnit.HOURS));

        assertThat(error)
                .isInstanceOf(NotationOutOfBoundsException.class)
                .hasMessage("invalid hours value 25, outside bounds 0 and 23");
    }

    @Test
    void shouldThrowExceptionIfInputIsBoundsButInputIsNotInteger() {
        String input = "2,3.5";

        Throwable error = catchThrowable(() -> parser.toValues(input, TimeUnit.HOURS));

        assertThat(error)
                .isInstanceOf(InvalidNotationException.class)
                .hasCauseInstanceOf(NumberFormatException.class)
                .hasMessage("3.5");
    }

}
