package uk.co.mruoc.exercises.smallestinteger;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class SmallestMissingPositiveIntegerTest {

    private final SmallestMissingPositiveInteger smallestMissingPositiveInteger = new SmallestMissingPositiveInteger();

    @Test
    void shouldHandleEmptyArray() {
        int[] inputs = new int[0];

        int smallestMissing = smallestMissingPositiveInteger.find(inputs);

        assertThat(smallestMissing).isEqualTo(1);
    }

    @Test
    void shouldHandle1InputValueGreaterThan1() {
        int[] inputs = new int[]{2};

        int smallestMissing = smallestMissingPositiveInteger.find(inputs);

        assertThat(smallestMissing).isEqualTo(1);
    }

    @Test
    void shouldHandle1InputValue1() {
        int[] inputs = new int[]{1};

        int smallestMissing = smallestMissingPositiveInteger.find(inputs);

        assertThat(smallestMissing).isEqualTo(2);
    }

    @Test
    void shouldHandleMinAndMaxValues() {
        int[] inputs = new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE};

        int smallestMissing = smallestMissingPositiveInteger.find(inputs);

        assertThat(smallestMissing).isEqualTo(1);
    }

    @Test
    void shouldFindSmallestMissingIntegerScenario1() {
        int[] inputs = new int[]{1, 3, 6, 4, 1, 2};

        int smallestMissing = smallestMissingPositiveInteger.find(inputs);

        assertThat(smallestMissing).isEqualTo(5);
    }

    @Test
    void shouldFindSmallestMissingIntegerScenario2() {
        int[] inputs = new int[]{1, 2, 3};

        int smallestMissing = smallestMissingPositiveInteger.find(inputs);

        assertThat(smallestMissing).isEqualTo(4);
    }

    @Test
    void shouldFindSmallestMissingIntegerScenario3() {
        int[] inputs = new int[]{-1, -3};

        int smallestMissing = smallestMissingPositiveInteger.find(inputs);

        assertThat(smallestMissing).isEqualTo(1);
    }

    @Test
    void shouldFindSmallestMissingIntegerScenario4() {
        int[] inputs = new int[]{10, 1, 9, 2, 8, 3, 7, 4, 5};

        int smallestMissing = smallestMissingPositiveInteger.find(inputs);

        assertThat(smallestMissing).isEqualTo(6);
    }

    @Test
    void shouldIgnoreNegativeValuesAndReturn1IfMissing() {
        int[] inputs = new int[]{-1, -2, 3, 4};

        int smallestMissing = smallestMissingPositiveInteger.find(inputs);

        assertThat(smallestMissing).isEqualTo(1);
    }

    @Test
    void shouldIgnoreNegativeValues() {
        int[] inputs = new int[]{-1, -2, 1, 2};

        int smallestMissing = smallestMissingPositiveInteger.find(inputs);

        assertThat(smallestMissing).isEqualTo(3);
    }

    @Test
    void shouldHandleLargeArray() {
        int[] inputs = IntStream.rangeClosed(1, 1000000).toArray();

        int smallestMissing = smallestMissingPositiveInteger.find(inputs);

        assertThat(smallestMissing).isEqualTo(1000001);
    }

}
