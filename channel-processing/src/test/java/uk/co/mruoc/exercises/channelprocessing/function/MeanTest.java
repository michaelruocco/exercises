package uk.co.mruoc.exercises.channelprocessing.function;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.input.InputSpec;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.exercises.channelprocessing.input.InputSpec.variable;

class MeanTest {

    private static final char IN_ID = 'X';
    private static final char OUT_ID = 'Y';

    private static final InputSpec IN_SPEC = variable(IN_ID);

    @Test
    void shouldCalculateMeanOfInputs() {
        Flux<Variables> variablesFlux = Flux.just(
                new Variables(IN_ID, new BigDecimal("1")),
                new Variables(IN_ID, new BigDecimal("4")),
                new Variables(IN_ID, new BigDecimal("10"))
        );
        ChannelFunction function = new Mean(IN_SPEC, OUT_ID);

        BigDecimal result = variablesFlux
                .map(variables -> function.apply(new Parameters(), variables))
                .last()
                .map(variables -> variables.get(OUT_ID))
                .blockOptional()
                .orElse(BigDecimal.ZERO);

        assertThat(result).isEqualTo(new BigDecimal("5"));
    }

}
