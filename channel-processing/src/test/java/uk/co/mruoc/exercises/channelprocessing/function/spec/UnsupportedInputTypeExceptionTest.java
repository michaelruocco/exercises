package uk.co.mruoc.exercises.channelprocessing.function.spec;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UnsupportedInputTypeExceptionTest {

    @Test
    void shouldReturnMessage() {
        String message = "message";

        Throwable error = new UnsupportedInputTypeException(message);

        Assertions.assertThat(error.getMessage()).isEqualTo(message);
    }
}
