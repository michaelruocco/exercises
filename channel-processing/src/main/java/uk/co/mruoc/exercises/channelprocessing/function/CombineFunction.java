package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RequiredArgsConstructor
@Slf4j
public class CombineFunction implements ChannelFunction {

    private final Collection<ChannelFunction> functions;

    public CombineFunction(ChannelFunction... functions) {
        this(Arrays.asList(functions));
    }

    @Override
    public Variables apply(Parameters parameters, Variables variables) {
        Variables combined = variables.copy();
        for (ChannelFunction function : functions) {
            combined = function.apply(parameters, combined);
        }
        return combined;
    }
}
