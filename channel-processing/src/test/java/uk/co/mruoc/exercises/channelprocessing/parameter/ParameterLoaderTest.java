package uk.co.mruoc.exercises.channelprocessing.parameter;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ParameterLoaderTest {

    private static final String TEST_PARAMETERS_PATH = "test-parameters.txt";

    private final ParameterLoader loader = new ParameterLoader();

    @Test
    void shouldLoadAllParameters() {
        Parameters parameters = loader.load(TEST_PARAMETERS_PATH);

        assertThat(parameters.size()).isEqualTo(3);
        assertThat(parameters.get('a')).isEqualTo(new BigDecimal("6.75"));
        assertThat(parameters.get('b')).isEqualTo(new BigDecimal("4.3"));
        assertThat(parameters.get('c')).isEqualTo(new BigDecimal("9.9"));
    }

    @Test
    void shouldLoadTestParameterA() {
        Parameters parameters = loader.load(TEST_PARAMETERS_PATH);

        assertThat(parameters.get('a')).isEqualTo(new BigDecimal("6.75"));
    }

    @Test
    void shouldLoadTestParameterB() {
        Parameters parameters = loader.load(TEST_PARAMETERS_PATH);

        assertThat(parameters.get('b')).isEqualTo(new BigDecimal("4.3"));
    }

    @Test
    void shouldLoadTestParameterC() {
        Parameters parameters = loader.load(TEST_PARAMETERS_PATH);

        assertThat(parameters.get('c')).isEqualTo(new BigDecimal("9.9"));
    }

}