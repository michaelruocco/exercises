package uk.co.mruoc.exercises.channelprocessing.function;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import uk.co.mruoc.exercises.channelprocessing.channel.ChannelLoader;
import uk.co.mruoc.exercises.channelprocessing.channel.Channels;
import uk.co.mruoc.exercises.channelprocessing.parameter.ParameterLoader;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class MetricBFunctionTest {

    private final ParameterLoader parameterLoader = new ParameterLoader();
    private final ChannelLoader channelLoader = new ChannelLoader();
    private final ChannelFunction function = new MetricBFunction();

    @Test
    void shouldCalculateMetricB() {
        Parameters parameters = parameterLoader.load("parameters.txt");

        BigDecimal b = Mono.just("channels.txt")
                .map(channelLoader::load)
                .flatMapMany(Channels::getArguments)
                .map(args -> function.apply(parameters, args))
                .map(args -> args.get('b'))
                .last()
                .block();

        assertThat(b).isEqualTo(new BigDecimal("6.26985216677700723479"));
    }
}
