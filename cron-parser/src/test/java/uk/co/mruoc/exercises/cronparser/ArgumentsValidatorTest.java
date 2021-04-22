package uk.co.mruoc.exercises.cronparser;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.catchThrowable;

class ArgumentsValidatorTest {

    private static final String USAGE_MESSAGE = "usage: please provide a valid cron expression";

    private final ArgumentsValidator validator = new ArgumentsValidator();

    @Test
    void shouldThrowExceptionWithUsageMessageIfNoArgumentsPassed() {
        String[] args = new String[0];

        Throwable error = catchThrowable(() -> validator.validate(args));

        assertThat(error).isInstanceOf(ParserException.class)
                .hasMessage(USAGE_MESSAGE);
    }

    @Test
    void shouldThrowExceptionWithInvalidMessageIfLessThanSixArgumentsPassed() {
        String[] args = new String[5];

        Throwable error = catchThrowable(() -> validator.validate(args));

        assertThat(error).isInstanceOf(ParserException.class)
                .hasMessage(toExpectedErrorMessage(args));
    }

    @Test
    void shouldThrowExceptionWithInvalidMessageIfGreaterThanSixArgumentsPassed() {
        String[] args = new String[7];

        Throwable error = catchThrowable(() -> validator.validate(args));

        assertThat(error).isInstanceOf(ParserException.class)
                .hasMessage(toExpectedErrorMessage(args));
    }

    @Test
    void shouldDoNothingIfSixArgumentsPassed() {
        String[] args = new String[6];

        assertThatCode(() -> validator.validate(args)).doesNotThrowAnyException();
    }

    private static String toExpectedErrorMessage(String[] args) {
        String expression = String.join(" ", args);
        return String.format("%s, invalid cron expression provided %s", USAGE_MESSAGE, expression);
    }

}
