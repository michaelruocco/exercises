package uk.co.mruoc.exercises.channelprocessing.function;

import com.fathzer.soft.javaluator.StaticVariableSet;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class EvaluatorTest {

    private final Evaluator evaluator = new Evaluator();

    @Test
    void shouldEvaluateAddition() {
        StaticVariableSet<BigDecimal> variables = new StaticVariableSet<>();
        variables.set("A", new BigDecimal("1"));
        variables.set("B", new BigDecimal("3.5"));

        BigDecimal result = evaluator.evaluate("A+B", variables);

        assertThat(result).isEqualTo(new BigDecimal("4.5"));
    }

    @Test
    void shouldEvaluateSubtraction() {
        StaticVariableSet<BigDecimal> variables = new StaticVariableSet<>();
        variables.set("C", new BigDecimal("2"));
        variables.set("D", new BigDecimal("4.5"));

        BigDecimal result = evaluator.evaluate("C-D", variables);

        assertThat(result).isEqualTo(new BigDecimal("-2.5"));
    }

    @Test
    void shouldEvaluateMultiplication() {
        StaticVariableSet<BigDecimal> variables = new StaticVariableSet<>();
        variables.set("E", new BigDecimal("3.5"));
        variables.set("F", new BigDecimal("2.3"));

        BigDecimal result = evaluator.evaluate("E*F", variables);

        assertThat(result).isEqualTo(new BigDecimal("8.05"));
    }

    @Test
    void shouldEvaluateDivision() {
        StaticVariableSet<BigDecimal> variables = new StaticVariableSet<>();
        variables.set("G", new BigDecimal("8"));
        variables.set("H", new BigDecimal("3"));

        BigDecimal result = evaluator.evaluate("G/H", variables);

        assertThat(result).isEqualTo(new BigDecimal("2.66666666666666666667"));
    }
}
