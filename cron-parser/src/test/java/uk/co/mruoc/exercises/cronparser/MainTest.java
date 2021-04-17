package uk.co.mruoc.exercises.cronparser;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemErrAndOut;

class MainTest {

    @Test
    void shouldParseValidCronExpression() throws Exception {
        String[] args = {"*/15", "0", "1,15", "*", "1-5", "/usr/bin/find"};

        String output = tapSystemErrAndOut(() -> Main.main(args));

        assertThat(output).contains("""
                minute 0 15 30 45
                hour 0
                day of month 1 15
                month 1 2 3 4 5 6 7 8 9 10 11 12
                day of week 1 2 3 4 5
                command /usr/bin/find
                """);
    }

    @Test
    void shouldPrintUsageIfNoExpressionPassed() throws Exception {
        String[] args = new String[0];

        String output = tapSystemErrAndOut(() -> Main.main(args));

        assertThat(output).contains("usage: please provide a cron expression as an argument");
    }

    @Test
    void shouldPrintErrorIfValueIsOutOfBounds() throws Exception {
        String[] args = {"60", "0", "1,15", "*", "1-5", "/usr/bin/find"};

        String output = tapSystemErrAndOut(() -> Main.main(args));

        assertThat(output).contains("invalid minutes value 60, outside bounds 0 and 59");
    }

    @Test
    void shouldPrintErrorIfNotationParserNotFound() throws Exception {
        String[] args = {"3.5", "0", "1,15", "*", "1-5", "/usr/bin/find"};

        String output = tapSystemErrAndOut(() -> Main.main(args));

        assertThat(output).contains("notation parser not found for value 3.5");
    }

}
