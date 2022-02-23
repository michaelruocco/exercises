package uk.co.mruoc.exercises.channelprocessing.argument;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
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

    public void addAll(Arguments otherArguments) {
        Collection<Character> otherIds = otherArguments.values.keySet();
        Collection<Character> thisIds = this.values.keySet();
        //if (!Collections.disjoint(otherIds, thisIds)) {
        //    throw new IllegalArgumentException(String.format("add all would overwrite existing values %s %s", this.values, otherArguments.values));
        //}
        this.values.putAll(otherArguments.values);
    }
}
