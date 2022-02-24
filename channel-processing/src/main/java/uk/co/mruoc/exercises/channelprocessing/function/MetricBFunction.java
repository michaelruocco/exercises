package uk.co.mruoc.exercises.channelprocessing.function;

import uk.co.mruoc.exercises.channelprocessing.Arguments;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

public class MetricBFunction implements ChannelFunction {

    private final ChannelFunction f1 = new Function1();
    private final ChannelFunction f2 = new Function2();
    private final ChannelFunction f3 = new Function3();

    @Override
    public Arguments apply(Parameters parameters, Arguments arguments) {
        Arguments Y = f1.apply(parameters, arguments);
        Arguments A = f3.apply(parameters, arguments);
        return f2.apply(parameters, Arguments.zip(Y, A));
    }
}
