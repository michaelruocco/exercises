package uk.co.mruoc.exercises.channelprocessing;

import com.google.common.collect.MapDifference;
import com.google.common.collect.MapDifference.ValueDifference;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class Arguments {

    private final Map<Character, BigDecimal> values;

    public Arguments(Character id, BigDecimal value) {
        this(new HashMap<>(Map.of(id, value)));
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

    public void addAll(Arguments other) {
        MapDifference<Character, BigDecimal> diff = Maps.difference(this.values, other.values);
        Map<Character, ValueDifference<BigDecimal>> entriesDiffering = diff.entriesDiffering();
        if (!entriesDiffering.isEmpty()) {
            throw new IllegalArgumentException(String.format("arguments cannot be combined as they have differing entries %s", entriesDiffering));
        }
        this.values.putAll(other.values);
    }
}
