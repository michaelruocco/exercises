package uk.co.mruoc.exercises.channelprocessing.function.spec;

import com.fathzer.soft.javaluator.StaticVariableSet;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

@RequiredArgsConstructor
@Data
public class FunctionSpec {

    private final String expression;
    private final String expressionValue;
    private final char target;
    private final Collection<InputSpec> inputSpecs;

    public FunctionSpec(String expression, InputSpec... inputSpecs) {
        this(expression, Arrays.asList(inputSpecs));
    }

    public FunctionSpec(String expression, Collection<InputSpec> inputSpecs) {
        this.expression = expression;
        this.expressionValue = removeTarget(expression);
        this.target = toTarget(expression);
        this.inputSpecs = inputSpecs;
    }

    public StaticVariableSet<BigDecimal> selectInputs(Parameters parameters, Variables variables) {
        StaticVariableSet<BigDecimal> set = new StaticVariableSet<>();
        inputSpecs.forEach(spec -> set.set(spec.getId(), spec.select(parameters, variables)));
        return set;
    }

    private static String removeTarget(String expression) {
        return expression.split("=")[1];
    }

    private static char toTarget(String expression) {
        return expression.split("=")[0].charAt(0);
    }
}
