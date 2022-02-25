package uk.co.mruoc.exercises.channelprocessing;

import com.google.common.collect.MapDifference;
import com.google.common.collect.MapDifference.ValueDifference;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Data
public class Variables {

    private final Map<Character, BigDecimal> values;

    public Variables() {
        this(new HashMap<>());
    }

    public Variables(Character id, BigDecimal value) {
        this(new HashMap<>(Map.of(id, value)));
    }

    public boolean contains(Character id) {
        return values.containsKey(id);
    }

    public BigDecimal get(Character id) {
        return values.get(id);
    }

    public void set(Character id, BigDecimal value) {
        values.put(id, value);
    }

    public int size() {
        return values.size();
    }

    public static Variables combine(Variables args1, Variables args2) {
        MapDifference<Character, BigDecimal> diff = Maps.difference(args1.values, args2.values);
        Map<Character, ValueDifference<BigDecimal>> entriesDiffering = diff.entriesDiffering();
        if (!entriesDiffering.isEmpty()) {
            throw new IllegalArgumentException(String.format("variables cannot be zipped as they have differing entries %s", entriesDiffering));
        }
        Map<Character, BigDecimal> copy = new HashMap<>(args1.values);
        copy.putAll(args2.values);
        return new Variables(copy);
    }
}
