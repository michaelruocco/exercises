package uk.co.mruoc.exercises.channelprocessing.function;

import com.fathzer.soft.javaluator.StaticVariableSet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.function.spec.FunctionSpec;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Slf4j
public class ArithmeticFunction implements ChannelFunction {

    private final FunctionSpec spec;
    private final Evaluator evaluator;

    public ArithmeticFunction(FunctionSpec spec) {
        this(spec, new Evaluator());
    }

    @Override
    public Variables apply(Parameters parameters, Variables inputs) {
        log.debug(spec.getExpression());
        StaticVariableSet<BigDecimal> variables = spec.selectInputs(parameters, inputs);
        BigDecimal result = evaluator.evaluate(spec.getExpressionValue(), variables);
        inputs.set(spec.getTarget(), result);
        return inputs;
    }
}
