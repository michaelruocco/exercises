package uk.co.mruoc.exercises.channelprocessing.function;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import uk.co.mruoc.exercises.channelprocessing.channel.ChannelLoader;
import uk.co.mruoc.exercises.channelprocessing.channel.Channels;
import uk.co.mruoc.exercises.channelprocessing.parameter.ParameterLoader;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FunctionsTest {

    private static final Parameters PARAMETERS = new ParameterLoader().load("parameters.txt");
    private static final Channels CHANNELS = new ChannelLoader().load("channels.txt");

    @ParameterizedTest
    @MethodSource("functionAndExpectedResult")
    void shouldCalculateExpectedValue(ChannelFunction function, char resultId, BigDecimal expectedResult) {
        BigDecimal b = CHANNELS.getVariables()
                .map(args -> function.apply(PARAMETERS, args))
                .map(args -> args.get(resultId))
                .last()
                .block();

        assertThat(b).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> functionAndExpectedResult() {
        return Stream.of(
                Arguments.of(new MetricBFunction(), 'b', new BigDecimal("6.26985216677700723479")),
                Arguments.of(new ChannelCFunction(), 'C', new BigDecimal("6.60697481117588923479"))
        );
    }
}
