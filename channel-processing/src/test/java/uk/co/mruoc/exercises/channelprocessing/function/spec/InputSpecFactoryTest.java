package uk.co.mruoc.exercises.channelprocessing.function.spec;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class InputSpecFactoryTest {

    private final InputSpecFactory factory = new InputSpecFactory();

    @Test
    void shouldThrowExceptionIfSpecInputTypeIsUnsupported() {
        String spec = "other-type(a)";

        Throwable error = catchThrowable(() -> factory.build(spec));

        assertThat(error)
                .isInstanceOf(UnsupportedInputTypeException.class)
                .hasMessage("other-type");
    }

    @Test
    void shouldBuildChannel() {
        String spec = "channel(B)";

        InputSpec metric = factory.build(spec);

        assertThat(metric).isInstanceOf(VariableInputSpec.class);
        assertThat(metric.getId()).isEqualTo("B");
    }

    @Test
    void shouldBuildMetric() {
        String spec = "metric(c)";

        InputSpec metric = factory.build(spec);

        assertThat(metric).isInstanceOf(VariableInputSpec.class);
        assertThat(metric.getId()).isEqualTo("c");
    }

    @Test
    void shouldBuildParameters() {
        String spec = "param(d)";

        InputSpec metric = factory.build(spec);

        assertThat(metric).isInstanceOf(ParameterInputSpec.class);
        assertThat(metric.getId()).isEqualTo("d");
    }

    @Test
    void shouldBuildConstant() {
        String spec = "constant(3.5)";

        InputSpec constant = factory.build(spec);

        assertThat(constant).isInstanceOf(ConstantInputSpec.class);
        assertThat(((ConstantInputSpec) constant).getValue()).isEqualTo(new BigDecimal("3.5"));
    }

}
