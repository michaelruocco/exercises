package uk.co.mruoc.exercises.channelprocessing.function;

import org.junit.jupiter.api.Test;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.input.InputSpec;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.exercises.channelprocessing.input.InputSpec.variable;

class MultiplyTest {

    private static final char IN_ID_1 = 'H';
    private static final char IN_ID_2 = 'I';
    private static final char OUT_ID = 'J';

    private static final InputSpec IN_SPEC_1 = variable(IN_ID_1);
    private static final InputSpec IN_SPEC_2 = variable(IN_ID_2);

    @Test
    void shouldMultiplyInputs() {
        Variables inputs = new Variables(Map.of(
                IN_ID_1, new BigDecimal("3"),
                IN_ID_2, new BigDecimal("4")
        ));
        ChannelFunction function = new Multiply(IN_SPEC_1, IN_SPEC_2, OUT_ID);

        Variables outputs = function.apply(new Parameters(), inputs);

        assertThat(outputs.get(OUT_ID)).isEqualTo(new BigDecimal("12"));
    }

}
