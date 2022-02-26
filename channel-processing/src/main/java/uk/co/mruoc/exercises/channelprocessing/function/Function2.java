package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import static uk.co.mruoc.exercises.channelprocessing.operation.OperationFactory.operation;
import static uk.co.mruoc.exercises.channelprocessing.input.InputSpec.variable;

@RequiredArgsConstructor
@Slf4j
public class Function2 implements ChannelFunction {

    private final Add add;
    private final Mean mean;

    public Function2() {
        this(new Add(variable('A'), variable('Y'), operation('+'), 'B'),
                new Mean(variable('B'), 'b'));
    }

    @Override
    public Variables apply(Parameters parameters, Variables variables) {
        return mean.apply(parameters, add.apply(parameters, variables));
    }

}
