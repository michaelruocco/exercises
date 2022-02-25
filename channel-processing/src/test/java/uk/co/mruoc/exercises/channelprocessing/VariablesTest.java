package uk.co.mruoc.exercises.channelprocessing;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class VariablesTest {

    @Test
    void shouldThrowExceptionIfAddAllHasDifferentValueForSameKey() {
        Variables args1 = new Variables('A', BigDecimal.ZERO);
        Variables args2 = new Variables('A', BigDecimal.ONE);

        Throwable error = catchThrowable(() -> Variables.combine(args1, args2));

        assertThat(error)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("variables cannot be zipped as they have differing entries {A=(0, 1)}");
    }

    @Test
    void shouldDoNothingIfAddAllHasSameValueForSameKey() {
        Variables args1 = new Variables('B', BigDecimal.ONE);
        Variables args2 = new Variables('B', BigDecimal.ONE);

        Variables combined = Variables.combine(args1, args2);

        assertThat(combined.size()).isEqualTo(1);
        assertThat(combined.get('B')).isEqualTo(BigDecimal.ONE);
    }

    @Test
    void shouldAddAllArgumentsIfThatAreNotAlreadyPresent() {
        Variables args1 = new Variables('C', BigDecimal.ONE);
        Variables args2 = new Variables('D', BigDecimal.ZERO);

        Variables combined = Variables.combine(args1, args2);

        assertThat(combined.size()).isEqualTo(2);
        assertThat(combined.get('C')).isEqualTo(BigDecimal.ONE);
        assertThat(combined.get('D')).isEqualTo(BigDecimal.ZERO);
    }

}
