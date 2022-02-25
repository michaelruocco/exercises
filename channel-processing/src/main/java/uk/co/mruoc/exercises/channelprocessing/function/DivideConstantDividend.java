package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;

import static uk.co.mruoc.exercises.channelprocessing.function.MathConstants.CONTEXT;

@RequiredArgsConstructor
@Slf4j
public class DivideConstantDividend implements ChannelFunction  {

    private final BigDecimal dividend;
    private final char divisorId;
    private final char resultId;

    @Override
    public Variables apply(Parameters parameters, Variables variables) {
        BigDecimal divisor = variables.get(divisorId);
        BigDecimal result = dividend.divide(divisor, CONTEXT);
        variables.set(resultId, result);
        log.debug("{}={}/{} {}={}/{}", result, dividend, divisorId, result, dividend, divisor);
        return variables;
    }

}
