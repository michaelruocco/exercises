package uk.co.mruoc.exercises.palindrome.domain.filter;

import java.util.Collection;
import java.util.function.Function;

public interface FilterFunction extends Function<Collection<String>, Collection<String>> {
    // intentionally blank
}
