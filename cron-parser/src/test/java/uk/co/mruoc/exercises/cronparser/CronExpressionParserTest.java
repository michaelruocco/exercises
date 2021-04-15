package uk.co.mruoc.exercises.cronparser;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CronExpressionParserTest {

    private final CronExpressionParser parser = new CronExpressionParser();

    @Test
    void shouldParseMinutesOfCronExpression() {
        String expression = "*/15 0 1,15 * 1-5 /usr/bin/find";

        CronResult result = parser.parse(expression);

        assertThat(result.getMinutes()).containsExactly(0, 15, 30, 45);
    }

    @Test
    void shouldParseHoursOfCronExpression() {
        String expression = "*/15 0 1,15 * 1-5 /usr/bin/find";

        CronResult result = parser.parse(expression);

        assertThat(result.getHours()).containsExactly(0);
    }

    @Test
    void shouldParseDaysOfMonthOfCronExpression() {
        String expression = "*/15 0 1,15 * 1-5 /usr/bin/find";

        CronResult result = parser.parse(expression);

        assertThat(result.getDaysOfMonth()).containsExactly(1, 15);
    }

    @Test
    void shouldParseMonthsOfCronExpression() {
        String expression = "*/15 0 1,15 * 1-5 /usr/bin/find";

        CronResult result = parser.parse(expression);

        assertThat(result.getMonths()).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    }

    @Test
    void shouldParseDayOfWeekOfCronExpression() {
        String expression = "*/15 0 1,15 * 1-5 /usr/bin/find";

        CronResult result = parser.parse(expression);

        assertThat(result.getDaysOfWeek()).containsExactly(1, 2, 3, 4, 5);
    }

    @Test
    void shouldParseCommandOfCronExpression() {
        String expression = "*/15 0 1,15 * 1-5 /usr/bin/find";

        CronResult result = parser.parse(expression);

        assertThat(result.getCommand()).isEqualTo("/usr/bin/find");
    }

}
