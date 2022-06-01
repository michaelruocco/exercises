package uk.co.mruoc.exercises.channelprocessing.function;

import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.util.Arrays;
import java.util.Collection;

public class CompositeFunction implements ChannelFunction {

    private final Collection<ChannelFunction> functions;

    public CompositeFunction(ChannelFunction... functions) {
        this(Arrays.asList(functions));
    }

    public CompositeFunction(Collection<ChannelFunction> functions) {
        this.functions = functions;
    }

    @Override
    public Variables apply(Parameters parameters, Variables variables) {
        for (ChannelFunction function : functions) {
            variables = function.apply(parameters, variables);
        }
        return variables;
    }

}
