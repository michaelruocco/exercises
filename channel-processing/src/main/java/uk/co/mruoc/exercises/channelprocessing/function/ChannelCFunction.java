package uk.co.mruoc.exercises.channelprocessing.function;

import uk.co.mruoc.exercises.channelprocessing.Arguments;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

public class ChannelCFunction implements ChannelFunction {

    private final ChannelFunction fb = new MetricBFunction();
    private final ChannelFunction f4 = new Function4();

    @Override
    public Arguments apply(Parameters parameters, Arguments arguments) {
        Arguments b = fb.apply(parameters, arguments);
        return f4.apply(parameters, b);
    }
}
