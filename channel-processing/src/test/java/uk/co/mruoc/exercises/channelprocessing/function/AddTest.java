package uk.co.mruoc.exercises.channelprocessing.function;

import org.junit.jupiter.api.Test;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.input.InputSpec;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.exercises.channelprocessing.input.InputSpec.variable;

class AddTest {

    private static final char IN_ID_1 = 'A';
    private static final char IN_ID_2 = 'B';
    private static final char OUT_ID = 'C';

    private static final InputSpec IN_SPEC_1 = variable(IN_ID_1);
    private static final InputSpec IN_SPEC_2 = variable(IN_ID_2);

    @Test
    void shouldAddInputs() {
        Variables inputs = new Variables(Map.of(
                IN_ID_1, new BigDecimal("2"),
                IN_ID_2, new BigDecimal("3")
        ));
        ChannelFunction function = new Add(IN_SPEC_1, IN_SPEC_2, OUT_ID);

        Variables outputs = function.apply(new Parameters(), inputs);

        assertThat(outputs.get(OUT_ID)).isEqualTo(new BigDecimal("5"));
    }

}
