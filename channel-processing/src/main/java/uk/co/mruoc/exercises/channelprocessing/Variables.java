package uk.co.mruoc.exercises.channelprocessing;

import com.google.common.collect.MapDifference;
import com.google.common.collect.MapDifference.ValueDifference;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Data
public class Variables {

    private final Map<Character, BigDecimal> values;

    public Variables(Map<Character, BigDecimal> values) {
        this.values = new HashMap<>(values);
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

    public Variables set(Character id, BigDecimal value) {
        Map<Character, BigDecimal> copy = new HashMap<>(values);
        copy.put(id, value);
        return new Variables(copy);
    }

    public int size() {
        return values.size();
    }

    public Variables setAll(Variables other) {
        MapDifference<Character, BigDecimal> diff = Maps.difference(this.values, other.values);
        Map<Character, ValueDifference<BigDecimal>> entriesDiffering = diff.entriesDiffering();
        if (!entriesDiffering.isEmpty()) {
            throw new IllegalArgumentException(String.format("variables cannot be zipped as they have differing entries %s", entriesDiffering));
        }
        Map<Character, BigDecimal> copy = new HashMap<>(this.values);
        copy.putAll(other.values);
        return new Variables(copy);
    }

    public Variables copy() {
        return new Variables(new HashMap<>(this.values));

    }

}
