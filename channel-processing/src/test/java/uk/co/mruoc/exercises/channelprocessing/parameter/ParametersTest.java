package uk.co.mruoc.exercises.channelprocessing.parameter;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ParametersTest {

    @Test
    void shouldReturnParameterValueIfPresent() {
        char id = 'a';
        BigDecimal expectedValue = BigDecimal.ONE;
        Parameters parameters = new Parameters(Map.of(id, expectedValue));

        BigDecimal value = parameters.get(id);

        assertThat(value).isEqualTo(expectedValue);
    }

    @Test
    void shouldThrowExceptionIfParameterValueNotPresent() {
        char id = 'b';
        Parameters parameters = new Parameters(Collections.emptyMap());

        Throwable error = catchThrowable(() -> parameters.get(id));

        assertThat(error)
                .isInstanceOf(ParameterNotFoundException.class)
                .hasMessage(Character.toString(id));
    }

}