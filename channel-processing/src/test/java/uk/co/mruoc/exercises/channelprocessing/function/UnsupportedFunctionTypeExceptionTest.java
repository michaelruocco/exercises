package uk.co.mruoc.exercises.channelprocessing.function;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UnsupportedFunctionTypeExceptionTest {

    @Test
    void shouldReturnMessage() {
        String message = "message";

        Throwable error = new UnsupportedFunctionTypeException(message);

        Assertions.assertThat(error.getMessage()).isEqualTo(message);
    }
}
