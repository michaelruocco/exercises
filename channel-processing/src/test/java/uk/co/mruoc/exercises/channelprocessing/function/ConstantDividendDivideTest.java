package uk.co.mruoc.exercises.channelprocessing.function;

import org.junit.jupiter.api.Test;
import uk.co.mruoc.exercises.channelprocessing.Arguments;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ConstantDividendDivideTest {

    private static final BigDecimal DIVIDEND = BigDecimal.TEN;
    private static final char DIVISOR_ID = 'E';
    private static final char RESULT_ID = 'F';

    @Test
    void shouldCalculateSum() {
        Arguments inputs = new Arguments();
        inputs.set(DIVISOR_ID, new BigDecimal("5"));
        ChannelFunction function = new DivideConstantDividend(DIVIDEND, DIVISOR_ID, RESULT_ID);

        Arguments outputs = function.apply(new Parameters(), inputs);

        assertThat(outputs.get(RESULT_ID)).isEqualTo(new BigDecimal("2"));
    }

}
