package uk.co.mruoc.exercises.palindrome.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PalindromeFinderTest {

    private final PalindromeFinder finder = new PalindromeFinder();

    @ParameterizedTest
    @MethodSource("inputsAndPalindromes")
    void shouldReturnAllPalindromesIfInputContainsMultiplePalindromes(String input, Collection<String> expectedPalindromes) {
        Collection<String> palindromes = finder.findPalindromes(input);

        assertThat(palindromes).containsExactlyElementsOf(expectedPalindromes);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "notpalindrometon",
            "this last phrase does not contain palindromes",
            ""
    })
    void shouldReturnEmptyIfInputIsNotAPalindrome(String input) {
        Collection<String> palindromes = finder.findPalindromes(input);

        assertThat(palindromes).isEmpty();
    }

    private static Stream<Arguments> inputsAndPalindromes() {
        return Stream.of(
                Arguments.of("racecar", List.of("racecar")),
                Arguments.of("was it a rat i saw", List.of("wasitaratisaw")),
                Arguments.of("was it your palindrome, level with me was it really?", List.of("level")),
                Arguments.of("anna & level", List.of("level", "anna"))
        );
    }
}
