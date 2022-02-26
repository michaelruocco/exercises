package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.input.InputSpec;
import uk.co.mruoc.exercises.channelprocessing.operation.Operation;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;

import static uk.co.mruoc.exercises.channelprocessing.operation.OperationFactory.add;

@RequiredArgsConstructor
@Slf4j
public class Add implements ChannelFunction {

    private final InputSpec inSpec1;
    private final InputSpec inSpec2;
    private final Operation operation;
    private final char resultId;

    public Add(InputSpec inSpec1, InputSpec inSpec2, char resultId) {
        this(inSpec1, inSpec2, add(), resultId);
    }

    @Override
    public Variables apply(Parameters parameters, Variables variables) {
        BigDecimal in1 = inSpec1.select(parameters, variables);
        BigDecimal in2 = inSpec2.select(parameters, variables);
        logAlgorithm();
        BigDecimal result = operation.apply(in1, in2);
        variables.set(resultId, result);
        return variables;
    }

    private void logAlgorithm() {
        log.debug(String.format("%s=%s%s%s", resultId, inSpec1.getId(), operation.getOperator(), inSpec2.getId()));
    }

}
