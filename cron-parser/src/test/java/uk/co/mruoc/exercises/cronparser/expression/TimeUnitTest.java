package uk.co.mruoc.exercises.cronparser.expression;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.catchThrowable;
import static uk.co.mruoc.exercises.cronparser.expression.TimeUnit.DAYS_OF_MONTH;
import static uk.co.mruoc.exercises.cronparser.expression.TimeUnit.DAYS_OF_WEEK;
import static uk.co.mruoc.exercises.cronparser.expression.TimeUnit.HOURS;
import static uk.co.mruoc.exercises.cronparser.expression.TimeUnit.MINUTES;
import static uk.co.mruoc.exercises.cronparser.expression.TimeUnit.MONTHS;

public class TimeUnitTest {

    @Nested
    class MinutesTest {

        @Test
        void shouldReturnAllValues() {
            assertThat(MINUTES.allValues()).containsExactly(allValuesIncluding(0, 59));
        }

        @Test
        void shouldDoNothingIfValuesAreValid() {
            assertThatCode(() -> MINUTES.validate(0, 59)).doesNotThrowAnyException();
        }

        @Test
        void shouldThrowExceptionIfValueIsOutOfBounds() {
            assertThat(catchThrowable(() -> MINUTES.validate(-1)))
                    .isInstanceOf(NotationOutOfBoundsException.class)
                    .hasMessage("invalid minutes value -1, outside bounds 0 and 59");

            assertThat(catchThrowable(() -> MINUTES.validate(60)))
                    .isInstanceOf(NotationOutOfBoundsException.class)
                    .hasMessage("invalid minutes value 60, outside bounds 0 and 59");
        }

    }

    @Nested
    class HoursTest {

        @Test
        void shouldReturnAllValues() {
            assertThat(HOURS.allValues()).containsExactly(allValuesIncluding(0, 23));
        }

        @Test
        void shouldDoNothingIfValuesAreValid() {
            assertThatCode(() -> HOURS.validate(0, 23)).doesNotThrowAnyException();
        }

        @Test
        void shouldThrowExceptionIfValueIsOutOfBounds() {
            assertThat(catchThrowable(() -> HOURS.validate(-1)))
                    .isInstanceOf(NotationOutOfBoundsException.class)
                    .hasMessage("invalid hours value -1, outside bounds 0 and 23");

            assertThat(catchThrowable(() -> HOURS.validate(24)))
                    .isInstanceOf(NotationOutOfBoundsException.class)
                    .hasMessage("invalid hours value 24, outside bounds 0 and 23");
        }

    }

    @Nested
    class DaysOfMonthTest {

        @Test
        void shouldReturnAllValues() {
            assertThat(DAYS_OF_MONTH.allValues()).containsExactly(allValuesIncluding(1, 31));
        }

        @Test
        void shouldDoNothingIfValuesAreValid() {
            assertThatCode(() -> DAYS_OF_MONTH.validate(1, 31)).doesNotThrowAnyException();
        }

        @Test
        void shouldThrowExceptionIfValueIsOutOfBounds() {
            assertThat(catchThrowable(() -> DAYS_OF_MONTH.validate(0)))
                    .isInstanceOf(NotationOutOfBoundsException.class)
                    .hasMessage("invalid days of month value 0, outside bounds 1 and 31");

            assertThat(catchThrowable(() -> DAYS_OF_MONTH.validate(32)))
                    .isInstanceOf(NotationOutOfBoundsException.class)
                    .hasMessage("invalid days of month value 32, outside bounds 1 and 31");
        }

    }

    @Nested
    class MonthsTest {

        @Test
        void shouldReturnAllValues() {
            assertThat(MONTHS.allValues()).containsExactly(allValuesIncluding(1, 12));
        }

        @Test
        void shouldDoNothingIfValuesAreValid() {
            assertThatCode(() -> MONTHS.validate(1, 12)).doesNotThrowAnyException();
        }

        @Test
        void shouldThrowExceptionIfValueIsOutOfBounds() {
            assertThat(catchThrowable(() -> MONTHS.validate(0)))
                    .isInstanceOf(NotationOutOfBoundsException.class)
                    .hasMessage("invalid months value 0, outside bounds 1 and 12");

            assertThat(catchThrowable(() -> MONTHS.validate(13)))
                    .isInstanceOf(NotationOutOfBoundsException.class)
                    .hasMessage("invalid months value 13, outside bounds 1 and 12");
        }

    }

    @Nested
    class DaysOfWeekTest {

        @Test
        void shouldReturnAllValues() {
            assertThat(DAYS_OF_WEEK.allValues()).containsExactly(allValuesIncluding(0, 6));
        }

        @Test
        void shouldDoNothingIfValuesAreValid() {
            assertThatCode(() -> DAYS_OF_WEEK.validate(0, 6)).doesNotThrowAnyException();
        }

        @Test
        void shouldThrowExceptionIfValueIsOutOfBounds() {
            assertThat(catchThrowable(() -> DAYS_OF_WEEK.validate(-1)))
                    .isInstanceOf(NotationOutOfBoundsException.class)
                    .hasMessage("invalid days of week value -1, outside bounds 0 and 6");

            assertThat(catchThrowable(() -> DAYS_OF_WEEK.validate(7)))
                    .isInstanceOf(NotationOutOfBoundsException.class)
                    .hasMessage("invalid days of week value 7, outside bounds 0 and 6");
        }

    }

    private static Integer[] allValuesIncluding(int start, int end) {
        return IntStream.rangeClosed(start, end).boxed().toArray(Integer[]::new);
    }

}
