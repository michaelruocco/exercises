package uk.co.mruoc.exercises.channelprocessing;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import uk.co.mruoc.exercises.channelprocessing.function.ChannelCFunction;
import uk.co.mruoc.exercises.channelprocessing.function.ChannelFunction;
import uk.co.mruoc.exercises.channelprocessing.parameter.ParameterLoader;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationLoadTest {

    private static final int CHANNEL_SIZE = 1000000;
    private static final Parameters PARAMETERS = new ParameterLoader().load("parameters.txt");

    @Test
    void shouldProcessLargeChannel() {
        Flux<Variables> channel = generateChannel();
        ChannelFunction function = new ChannelCFunction();

        BigDecimal result = channel.map(variables -> function.apply(PARAMETERS, variables))
                .map(variables -> variables.get('C'))
                .last()
                .block();

        assertThat(result).isEqualTo(new BigDecimal("2000003.50001439271333"));
    }

    private static Flux<Variables> generateChannel() {
        return Flux.range(1, CHANNEL_SIZE + 1).map(i -> new Variables('X', BigDecimal.valueOf(i)));
    }
}
