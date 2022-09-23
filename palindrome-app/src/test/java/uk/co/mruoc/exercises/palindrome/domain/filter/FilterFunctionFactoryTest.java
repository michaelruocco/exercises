package uk.co.mruoc.exercises.palindrome.domain.filter;

import org.junit.jupiter.api.Test;
import uk.co.mruoc.exercises.palindrome.domain.RemoveDuplicatesFilterFunction;

import static org.assertj.core.api.Assertions.assertThat;

class FilterFunctionFactoryTest {

    private final FilterFunctionFactory factory = new FilterFunctionFactory();

    @Test
    void shouldReturnNoOpFilterFunctionForNoneFilter() {
        Filter filter = Filter.NONE;

        FilterFunction function = factory.toFunction(filter);

        assertThat(function).isInstanceOf(NoOpFilterFunction.class);
    }

    @Test
    void shouldReturnRemoveDuplicatesFunctionForRemoveDuplicatesFilter() {
        Filter filter = Filter.REMOVE_DUPLICATES;

        FilterFunction function = factory.toFunction(filter);

        assertThat(function).isInstanceOf(RemoveDuplicatesFilterFunction.class);
    }
}
