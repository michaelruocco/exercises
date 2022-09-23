package uk.co.mruoc.exercises.palindrome.domain.filter;

import uk.co.mruoc.exercises.palindrome.domain.RemoveDuplicatesFilterFunction;

public class FilterFunctionFactory {

    public FilterFunction toFunction(Filter filter) {
        return switch (filter) {
            case REMOVE_DUPLICATES -> new RemoveDuplicatesFilterFunction();
            case NONE -> new NoOpFilterFunction();
        };
    }
}
