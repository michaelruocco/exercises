package uk.co.mruoc.exercises.cronparser;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemErr;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemOut;

class MainTest {

    @Test
    void shouldParseValidCronExpression() throws Exception {
        String[] args = {"*/15", "0", "1,15", "*", "1-5", "/usr/bin/find"};

        String output = tapSystemOut(() -> Main.main(args));

        assertThat(output).isEqualTo("""
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

        String output = tapSystemErr(() -> Main.main(args));

        assertThat(output).isEqualToIgnoringNewLines("usage: please provide a cron expression as an argument");
    }

    @Test
    void shouldPrintErrorIfValueIsOutOfBounds() throws Exception {
        String[] args = {"60", "0", "1,15", "*", "1-5", "/usr/bin/find"};

        String output = tapSystemErr(() -> Main.main(args));

        assertThat(output).isEqualToIgnoringNewLines("invalid minutes value 60, outside bounds 0 and 59");
    }

    @Test
    void shouldPrintErrorIfNotationParserNotFound() throws Exception {
        String[] args = {"3.5", "0", "1,15", "*", "1-5", "/usr/bin/find"};

        String output = tapSystemErr(() -> Main.main(args));

        assertThat(output).isEqualToIgnoringNewLines("notation parser not found for value 3.5");
    }

}
