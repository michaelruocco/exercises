package uk.co.mruoc.exercises.channelprocessing.spec;

import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;

public interface InputSpec {

    String getId();

    BigDecimal select(Parameters parameters, Variables variables);

    static InputSpec variable(char id) {
        return new VariableInputSpec(id);
    }

    static InputSpec parameter(char id) {
        return new ParameterInputSpec(id);
    }

    static InputSpec constant(BigDecimal value) {
        return new ConstantInputSpec(value);
    }
}
