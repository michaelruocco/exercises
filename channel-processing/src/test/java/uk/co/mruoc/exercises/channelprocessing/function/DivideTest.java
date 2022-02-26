package uk.co.mruoc.exercises.channelprocessing.function;

import org.junit.jupiter.api.Test;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.input.InputSpec;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.exercises.channelprocessing.input.InputSpec.constant;
import static uk.co.mruoc.exercises.channelprocessing.input.InputSpec.variable;

class DivideTest {

    private static final InputSpec DIVIDEND_SPEC = constant(BigDecimal.TEN);
    private static final char DIVISOR_ID = 'E';
    private static final InputSpec DIVISOR_SPEC = variable(DIVISOR_ID);
    private static final char RESULT_ID = 'F';

    @Test
    void shouldDivideDividendByDivisor() {
        Variables inputs = new Variables(DIVISOR_ID, new BigDecimal("5"));
        ChannelFunction function = new Divide(DIVIDEND_SPEC, DIVISOR_SPEC, RESULT_ID);

        Variables outputs = function.apply(new Parameters(), inputs);

        assertThat(outputs.get(RESULT_ID)).isEqualTo(new BigDecimal("2"));
    }

}
