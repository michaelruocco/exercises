package uk.co.mruoc.exercises.binarygap;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class BinaryGapTest {

    private final BinaryGap binaryGap = new BinaryGap();

    @ParameterizedTest(name = "{index} should return gap of {1} for value {0}")
    @CsvSource({
            "9,2",
            "15,0",
            "20,1",
            "32,0",
            "529,4",
            "1041,5",
            "2147483647,0"
    })
    void shouldReturnBinaryGapForValue(int value, int expectedGap) {
        int gap = binaryGap.calculate(value);

        assertThat(gap).isEqualTo(expectedGap);
    }

}
