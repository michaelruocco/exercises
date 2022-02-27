package uk.co.mruoc.exercises.channelprocessing.function;

import org.junit.jupiter.api.Test;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.function.spec.FunctionSpec;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.exercises.channelprocessing.function.spec.InputSpec.channel;
import static uk.co.mruoc.exercises.channelprocessing.function.spec.InputSpec.constant;
import static uk.co.mruoc.exercises.channelprocessing.function.spec.InputSpec.parameter;

class ArithmeticFunctionTest {

    @Test
    void shouldPerformAddition() {
        Variables inputs = new Variables('A', new BigDecimal("2"));
        Parameters parameters = new Parameters('b', new BigDecimal("3"));
        FunctionSpec spec = new FunctionSpec("C=A+b", channel('A'), parameter('b'));
        ChannelFunction function = new ArithmeticFunction(spec);

        Variables outputs = function.apply(parameters, inputs);

        assertThat(outputs.get(spec.getTarget())).isEqualTo(new BigDecimal("5"));
    }

    @Test
    void shouldPerformDivision() {
        Variables inputs = new Variables('E', new BigDecimal("5"));
        FunctionSpec spec = new FunctionSpec("F=10/E", constant(10), channel('E'));
        ChannelFunction function = new ArithmeticFunction(spec);

        Variables outputs = function.apply(new Parameters(), inputs);

        assertThat(outputs.get(spec.getTarget())).isEqualTo(new BigDecimal("2"));
    }

    @Test
    void shouldPerformMultiplication() {
        Variables inputs = new Variables('H', new BigDecimal("3"));
        Parameters parameters = new Parameters('i', new BigDecimal("4"));
        FunctionSpec spec = new FunctionSpec("J=H*i", channel('H'), parameter('i'));
        ChannelFunction function = new ArithmeticFunction(spec);

        Variables outputs = function.apply(parameters, inputs);

        assertThat(outputs.get(spec.getTarget())).isEqualTo(new BigDecimal("12"));
    }

    @Test
    void shouldPerformSubtraction() {
        Variables inputs = new Variables('K', new BigDecimal("6"));
        Parameters parameters = new Parameters('l', new BigDecimal("8"));
        FunctionSpec spec = new FunctionSpec("M=K-l", channel('K'), parameter('l'));
        ChannelFunction function = new ArithmeticFunction(spec);

        Variables outputs = function.apply(parameters, inputs);

        assertThat(outputs.get(spec.getTarget())).isEqualTo(new BigDecimal("-2"));
    }

}
