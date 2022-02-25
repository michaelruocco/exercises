package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Slf4j
public class Add implements ChannelFunction  {

    private final char inId1;
    private final char inId2;
    private final char outId;

    @Override
    public Variables apply(Parameters parameters, Variables variables) {
        BigDecimal inValue1 = variables.get(inId1);
        BigDecimal inValue2 = variables.get(inId2);
        BigDecimal outValue = inValue1.add(inValue2);
        variables.set(outId, outValue);
        log.debug("{}={}+{} {}={}+{}", outId, inId1, inId2, outValue, inValue1, inValue2);
        return variables;
    }

}
