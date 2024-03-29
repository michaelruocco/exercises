package uk.co.mruoc.exercises.channelprocessing.parameter;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class Parameters {

    private final Map<Character, BigDecimal> values;

    public Parameters() {
        this(Collections.emptyMap());
    }

    public Parameters(char id, BigDecimal value) {
        this(Map.of(id, value));
    }

    public BigDecimal get(char id) {
        return Optional.ofNullable(values.get(id))
                .orElseThrow(() -> new ParameterNotFoundException(id));
    }

    public int size() {
        return values.size();
    }

}
