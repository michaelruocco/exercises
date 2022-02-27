package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.spec.FunctionSpec;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import static uk.co.mruoc.exercises.channelprocessing.spec.InputSpec.variable;

@RequiredArgsConstructor
@Slf4j
public class Function2 implements ChannelFunction {

    private final ChannelFunction add;
    private final ChannelFunction mean;

    public Function2() {
        this(new ArithmeticFunction(new FunctionSpec("B=A+Y", variable('A'), variable('Y'))),
                new MeanFunction(variable('B'), 'b'));
    }

    @Override
    public Variables apply(Parameters parameters, Variables variables) {
        return mean.apply(parameters, add.apply(parameters, variables));
    }

}
