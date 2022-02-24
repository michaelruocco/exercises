package uk.co.mruoc.exercises.channelprocessing;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ArgumentsTest {

    @Test
    void shouldThrowExceptionIfAddAllHasDifferentValueForSameKey() {
        Arguments args1 = new Arguments('A', BigDecimal.ZERO);
        Arguments args2 = new Arguments('A', BigDecimal.ONE);

        Throwable error = catchThrowable(() -> Arguments.combine(args1, args2));

        assertThat(error)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("arguments cannot be zipped as they have differing entries {A=(0, 1)}");
    }

    @Test
    void shouldDoNothingIfAddAllHasSameValueForSameKey() {
        Arguments args1 = new Arguments('B', BigDecimal.ONE);
        Arguments args2 = new Arguments('B', BigDecimal.ONE);

        Arguments combined = Arguments.combine(args1, args2);

        assertThat(combined.size()).isEqualTo(1);
        assertThat(combined.get('B')).isEqualTo(BigDecimal.ONE);
    }

    @Test
    void shouldAddAllArgumentsIfThatAreNotAlreadyPresent() {
        Arguments args1 = new Arguments('C', BigDecimal.ONE);
        Arguments args2 = new Arguments('D', BigDecimal.ZERO);

        Arguments combined = Arguments.combine(args1, args2);

        assertThat(combined.size()).isEqualTo(2);
        assertThat(combined.get('C')).isEqualTo(BigDecimal.ONE);
        assertThat(combined.get('D')).isEqualTo(BigDecimal.ZERO);
    }

}