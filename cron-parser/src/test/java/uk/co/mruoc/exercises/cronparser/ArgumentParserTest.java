package uk.co.mruoc.exercises.cronparser;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ArgumentParserTest {

    private final ArgumentParser parser = new ArgumentParser();

    @Test
    void shouldEmptyOptionalIfArgumentArrayIsEmpty() {
        String[] args = new String[0];

        Optional<String> argument = parser.toExpression(args);

        assertThat(argument).isEmpty();
    }

    @Test
    void shouldJoinArgumentsToSingleStringSeparatedBySpaces() {
        String[] args = new String[] { "part-1", "part-2" };

        Optional<String> argument = parser.toExpression(args);

        assertThat(argument).contains("part-1 part-2");
    }

    @Test
    void shouldIgnoreEmptyArrayElements() {
        String[] args = new String[] { "part-1", null, "part-2" };

        Optional<String> argument = parser.toExpression(args);

        assertThat(argument).contains("part-1  part-2");
    }

}
