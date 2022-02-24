package uk.co.mruoc.exercises.channelprocessing.function;

import org.junit.jupiter.api.Test;
import uk.co.mruoc.exercises.channelprocessing.Arguments;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class SumTest {

    private static final char IN_ID_1 = 'A';
    private static final char IN_ID_2 = 'B';
    private static final char OUT_ID = 'C';

    @Test
    void shouldCalculateSum() {
        Arguments inputs = new Arguments();
        inputs.set(IN_ID_1, new BigDecimal("2"));
        inputs.set(IN_ID_2, new BigDecimal("3"));
        ChannelFunction function = new Sum(IN_ID_1, IN_ID_2, OUT_ID);

        Arguments outputs = function.apply(new Parameters(), inputs);

        assertThat(outputs.get(OUT_ID)).isEqualTo(new BigDecimal("5"));
    }

}
