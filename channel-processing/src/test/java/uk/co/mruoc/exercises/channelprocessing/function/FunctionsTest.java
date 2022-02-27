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

    private static final FunctionLoader FUNCTION_LOADER = new FunctionLoader();
    private static final Parameters PARAMETERS = new ParameterLoader().load("parameters.txt");
    private static final Channels CHANNELS = new ChannelLoader().load("channels.txt");

    @ParameterizedTest
    @MethodSource("functionAndExpectedResult")
    void shouldCalculateExpectedValue(ChannelFunction function, char resultId, String expectedResult) {
        BigDecimal b = CHANNELS.getVariables()
                .map(args -> function.apply(PARAMETERS, args))
                .map(args -> args.get(resultId))
                .last()
                .block();

        assertThat(b).isEqualTo(new BigDecimal(expectedResult));
    }

    private static Stream<Arguments> functionAndExpectedResult() {
        return Stream.of(
                Arguments.of(FUNCTION_LOADER.load("functions-metric-b.csv"), 'b', "6.26985216677700723479"),
                Arguments.of(FUNCTION_LOADER.load("functions-channel-c.csv"), 'C', "6.60697481117588923479"),
                Arguments.of(new MetricBFunction(), 'b', "6.26985216677700723479"),
                Arguments.of(new ChannelCFunction(), 'C', "6.60697481117588923479")
        );
    }
}
