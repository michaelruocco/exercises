package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.Arguments;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Slf4j
public class Sum implements ChannelFunction  {

    private final char inId1;
    private final char inId2;
    private final char outId;

    @Override
    public Arguments apply(Parameters parameters, Arguments arguments) {
        BigDecimal inValue1 = arguments.get(inId1);
        BigDecimal inValue2 = arguments.get(inId2);
        BigDecimal outValue = inValue1.add(inValue2);
        arguments.set(outId, outValue);
        log.debug("{}={}+{} {}={}+{}", outId, inId1, inId2, outValue, inValue1, inValue2);
        return arguments;
    }

}
