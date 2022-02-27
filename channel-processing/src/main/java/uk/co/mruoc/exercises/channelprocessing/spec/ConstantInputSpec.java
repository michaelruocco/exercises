package uk.co.mruoc.exercises.channelprocessing.spec;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Data
class ConstantInputSpec implements InputSpec {

    private final BigDecimal value;

    @Override
    public String getId() {
        return value.toString();
    }

    @Override
    public BigDecimal select(Parameters parameters, Variables variables) {
        return value;
    }

}
