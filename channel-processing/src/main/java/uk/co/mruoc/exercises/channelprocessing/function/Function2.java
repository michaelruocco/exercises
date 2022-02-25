package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

@RequiredArgsConstructor
@Slf4j
public class Function2 implements ChannelFunction {

    private final Add add;
    private final Mean mean;

    public Function2() {
        this(new Add('A', 'Y', 'B'), new Mean('B', 'b'));
    }

    @Override
    public Variables apply(Parameters parameters, Variables variables) {
        Variables sumArgs = add.apply(parameters, variables);
        return mean.apply(parameters, sumArgs);
    }

}
