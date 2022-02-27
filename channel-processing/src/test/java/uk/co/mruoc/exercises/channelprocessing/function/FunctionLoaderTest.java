package uk.co.mruoc.exercises.channelprocessing.function;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class FunctionLoaderTest {

    private final FunctionLoader loader = new FunctionLoader();

    @Test
    void shouldThrowExceptionIfSpecTypeIsUnsupported() {
        String line = "other-type,arithmetic~B=A+Y~channel(A)~channel(Y)";

        Throwable error = catchThrowable(() -> loader.toFunction(line));

        assertThat(error)
                .isInstanceOf(UnsupportedSpecTypeException.class)
                .hasMessage("other-type");
    }

    @Test
    void shouldThrowExceptionIfFunctionTypeIsUnsupported() {
        String line = "single,other-type~B=A+Y~channel(A)~channel(Y)";

        Throwable error = catchThrowable(() -> loader.toFunction(line));

        assertThat(error)
                .isInstanceOf(UnsupportedFunctionTypeException.class)
                .hasMessage("other-type");
    }

}
