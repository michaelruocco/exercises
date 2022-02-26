package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.extern.slf4j.Slf4j;

import static uk.co.mruoc.exercises.channelprocessing.operation.OperationFactory.operation;
import static uk.co.mruoc.exercises.channelprocessing.input.InputSpec.variable;

@Slf4j
public class Function4 extends Add {

    public Function4() {
        super(variable('X'), variable('b'), operation('+'), 'C');
    }

}
