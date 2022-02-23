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

        Throwable error = catchThrowable(() -> args1.addAll(args2));

        assertThat(error)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("arguments cannot be combined as they have differing entries {A=(0, 1)}");
    }

    @Test
    void shouldDoNothingIfAddAllHasSameValueForSameKey() {
        Arguments args1 = new Arguments('B', BigDecimal.ONE);
        Arguments args2 = new Arguments('B', BigDecimal.ONE);

        args1.addAll(args2);

        assertThat(args1.size()).isEqualTo(1);
        assertThat(args1.get('B')).isEqualTo(BigDecimal.ONE);
    }

    @Test
    void shouldAddAllArgumentsIfThatAreNotAlreadyPresent() {
        Arguments args1 = new Arguments('C', BigDecimal.ONE);
        Arguments args2 = new Arguments('D', BigDecimal.ZERO);

        args1.addAll(args2);

        assertThat(args1.size()).isEqualTo(2);
        assertThat(args1.get('C')).isEqualTo(BigDecimal.ONE);
        assertThat(args1.get('D')).isEqualTo(BigDecimal.ZERO);
    }

}