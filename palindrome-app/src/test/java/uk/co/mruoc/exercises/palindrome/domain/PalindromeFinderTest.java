package uk.co.mruoc.exercises.palindrome.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PalindromeFinderTest {

    private final PalindromeRequest request = givenRequestWithMinLength(3);

    private final PalindromeFinder finder = new PalindromeFinder();

    @ParameterizedTest
    @CsvSource(value = {
            "racecar: racecar",
            "was it a rat i saw: wasitaratisaw",
            "palindromes level phrase: level",
            "anna & level: level,anna",
            "anna & level and racecar: racecar,level,anna"
    }, delimiter = ':')
    void shouldReturnAllPalindromesIfInputContainsMultiplePalindromes(String input, String expectedPalindromes) {
        when(request.getInput()).thenReturn(input);

        Collection<String> palindromes = finder.findPalindromes(request);

        assertThat(palindromes).containsExactly(expectedPalindromes.split(","));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "notpalindrometon",
            "this last phrase does not contain palindromes",
            ""
    })
    void shouldReturnEmptyIfInputIsNotAPalindrome(String input) {
        when(request.getInput()).thenReturn(input);

        Collection<String> palindromes = finder.findPalindromes(request);

        assertThat(palindromes).isEmpty();
    }

    private static PalindromeRequest givenRequestWithMinLength(int minLength) {
        PalindromeRequest request = mock(PalindromeRequest.class);
        when(request.getMinLength()).thenReturn(minLength);
        return request;
    }
}
