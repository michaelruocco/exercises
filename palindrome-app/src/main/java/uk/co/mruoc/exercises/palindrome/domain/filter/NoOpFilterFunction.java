package uk.co.mruoc.exercises.palindrome.domain.filter;

import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

@Slf4j
public class NoOpFilterFunction implements FilterFunction {

    @Override
    public Collection<String> apply(Collection<String> inputs) {
        return inputs;
    }
}
