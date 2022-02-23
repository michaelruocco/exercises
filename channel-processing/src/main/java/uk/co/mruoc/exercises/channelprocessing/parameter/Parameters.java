package uk.co.mruoc.exercises.channelprocessing.parameter;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class Parameters {

    private final Map<Character, BigDecimal> values;

    public BigDecimal get(char id) {
        return Optional.ofNullable(values.get(id))
                .orElseThrow(() -> new ParameterNotFoundException(id));
    }

}
