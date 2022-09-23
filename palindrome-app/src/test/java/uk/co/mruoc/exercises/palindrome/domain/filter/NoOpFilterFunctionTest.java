package uk.co.mruoc.exercises.palindrome.domain.filter;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NoOpFilterFunctionTest {

    private final NoOpFilterFunction filterFunction = new NoOpFilterFunction();

    @Test
    void shouldNotFilter() {
        Collection<String> input = List.of("input1", "input2", "input3");

        Collection<String> result = filterFunction.apply(input);

        assertThat(result).isEqualTo(input);
    }
}
