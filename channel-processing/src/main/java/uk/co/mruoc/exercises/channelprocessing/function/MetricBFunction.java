package uk.co.mruoc.exercises.channelprocessing.function;

import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

public class MetricBFunction implements ChannelFunction {

    private final ChannelFunction f1 = new Function1();
    private final ChannelFunction f2 = new Function2();
    private final ChannelFunction f3 = new Function3();

    @Override
    public Variables apply(Parameters parameters, Variables variables) {
        Variables Y = f1.apply(parameters, variables);
        Variables A = f3.apply(parameters, variables);
        return f2.apply(parameters, Variables.combine(Y, A));
    }
}
