package uk.co.mruoc.exercises.channelprocessing.function;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.function.spec.InputSpec;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.exercises.channelprocessing.function.spec.InputSpecFactory.channel;

class MeanFunctionTest {

    private static final char IN_ID = 'X';
    private static final char OUT_ID = 'Y';

    private static final InputSpec IN_SPEC = channel(IN_ID);

    @Test
    void shouldCalculateMeanOfInputs() {
        Flux<Variables> channelX = Flux.just(
                new Variables(IN_ID, new BigDecimal("1")),
                new Variables(IN_ID, new BigDecimal("4")),
                new Variables(IN_ID, new BigDecimal("10"))
        );
        ChannelFunction function = new MeanFunction(IN_SPEC, OUT_ID);

        BigDecimal result = channelX
                .map(variables -> function.apply(new Parameters(), variables))
                .last()
                .map(variables -> variables.get(OUT_ID))
                .blockOptional()
                .orElse(BigDecimal.ZERO);

        assertThat(result).isEqualTo(new BigDecimal("5"));
    }

}
