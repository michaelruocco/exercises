package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

@RequiredArgsConstructor
public class CombineFunction implements ChannelFunction {

    private final ChannelFunction f1;
    private final ChannelFunction f2;

    @Override
    public Variables apply(Parameters parameters, Variables variables) {
        Variables out1 = f1.apply(parameters, variables);
        Variables out2 = f2.apply(parameters, variables);
        return Variables.combine(out1, out2);
    }
}
