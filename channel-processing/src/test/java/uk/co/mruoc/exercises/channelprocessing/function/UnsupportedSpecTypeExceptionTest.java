package uk.co.mruoc.exercises.channelprocessing.function;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UnsupportedSpecTypeExceptionTest {

    @Test
    void shouldReturnMessage() {
        String message = "message";

        Throwable error = new UnsupportedSpecTypeException(message);

        Assertions.assertThat(error.getMessage()).isEqualTo(message);
    }
}
