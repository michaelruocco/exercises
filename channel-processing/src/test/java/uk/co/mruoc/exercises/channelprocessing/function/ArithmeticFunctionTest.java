package uk.co.mruoc.exercises.channelprocessing.function;

import org.junit.jupiter.api.Test;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.spec.FunctionSpec;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.exercises.channelprocessing.spec.InputSpec.constant;
import static uk.co.mruoc.exercises.channelprocessing.spec.InputSpec.variable;

class ArithmeticFunctionTest {

    @Test
    void shouldPerformAddition() {
        Variables inputs = new Variables(Map.of(
                'A', new BigDecimal("2"),
                'B', new BigDecimal("3")
        ));
        FunctionSpec spec = new FunctionSpec("C=A+B", variable('A'), variable('B'));
        ChannelFunction function = new ArithmeticFunction(spec);

        Variables outputs = function.apply(new Parameters(), inputs);

        assertThat(outputs.get(spec.getTarget())).isEqualTo(new BigDecimal("5"));
    }

    @Test
    void shouldPerformDivision() {
        Variables inputs = new Variables('E', new BigDecimal("5"));
        FunctionSpec spec = new FunctionSpec("F=10/E", constant(new BigDecimal(10)), variable('E'));
        ChannelFunction function = new ArithmeticFunction(spec);

        Variables outputs = function.apply(new Parameters(), inputs);

        assertThat(outputs.get(spec.getTarget())).isEqualTo(new BigDecimal("2"));
    }

    @Test
    void shouldPerformMultiplication() {
        Variables inputs = new Variables(Map.of(
                'H', new BigDecimal("3"),
                'I', new BigDecimal("4")
        ));
        FunctionSpec spec = new FunctionSpec("J=H*I", variable('H'), variable('I'));
        ChannelFunction function = new ArithmeticFunction(spec);

        Variables outputs = function.apply(new Parameters(), inputs);

        assertThat(outputs.get(spec.getTarget())).isEqualTo(new BigDecimal("12"));
    }

    @Test
    void shouldPerformSubtraction() {
        Variables inputs = new Variables(Map.of(
                'K', new BigDecimal("6"),
                'L', new BigDecimal("8")
        ));
        FunctionSpec spec = new FunctionSpec("M=K-L", variable('K'), variable('L'));
        ChannelFunction function = new ArithmeticFunction(spec);

        Variables outputs = function.apply(new Parameters(), inputs);

        assertThat(outputs.get(spec.getTarget())).isEqualTo(new BigDecimal("-2"));
    }

}
