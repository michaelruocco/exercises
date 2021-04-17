package uk.co.mruoc.exercises.cronparser.expression.notation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParserNotFoundExceptionTest {

    @Test
    void shouldReturnMessage() {
        String input = "my-input";

        Throwable error = new ParserNotFoundException(input);

        assertThat(error.getMessage()).isEqualTo("parser not found for value my-input");
    }

}
