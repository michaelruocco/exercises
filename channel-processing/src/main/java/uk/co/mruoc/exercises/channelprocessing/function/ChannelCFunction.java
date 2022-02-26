package uk.co.mruoc.exercises.channelprocessing.function;

import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

public class ChannelCFunction implements ChannelFunction {

    private final ChannelFunction fb = new MetricBFunction();
    private final ChannelFunction f4 = new Function4();

    @Override
    public Variables apply(Parameters parameters, Variables variables) {
        return f4.apply(parameters, fb.apply(parameters, variables));
    }
}
