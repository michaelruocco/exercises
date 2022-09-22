package uk.co.mruoc.exercises.palindrome.domain;

import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class PalindromeFinderTest {

    private final PalindromeFinder calculator = new PalindromeFinder();

    @Test
    void shouldReturnAllPalindromesFromInputIfInputIsAPalindrome() {
        String input = "racecar";

        Collection<String> palindromes = calculator.findPalindromes(input);

        assertThat(palindromes).containsExactly("racecar");
    }

    @Test
    void shouldReturnAllPalindromesFromInputIfInputIsAPalindromePhrase() {
        String input = "was it a rat i saw";

        Collection<String> palindromes = calculator.findPalindromes(input);

        assertThat(palindromes).containsExactly("wasitaratisaw");
    }

    @Test
    void shouldReturnPalindromesIfInputContainsPalindromes() {
        String input = "was it your palindrome, level with me was it really?";

        Collection<String> palindromes = calculator.findPalindromes(input);

        assertThat(palindromes).containsExactly("level");
    }

    @Test
    void shouldReturnEmptyIfInputIsNotAPalindrome() {
        String input = "notpalindrometon";

        Collection<String> palindromes = calculator.findPalindromes(input);

        assertThat(palindromes).isEmpty();
    }

    @Test
    void shouldReturnEmptyIfInputDoesNotContainAPalindrome() {
        String input = "this last phrase does not contain palindromes";

        Collection<String> palindromes = calculator.findPalindromes(input);

        assertThat(palindromes).isEmpty();
    }

    @Test
    void shouldReturnEmptyIfInputIsEmpty() {
        String input = "";

        Collection<String> palindromes = calculator.findPalindromes(input);

        assertThat(palindromes).isEmpty();
    }

    @Test
    void shouldReturnAllPalindromesIfInputContainsMultiplePalindromes() {
        String input = "anna & level";

        Collection<String> palindromes = calculator.findPalindromes(input);

        assertThat(palindromes).containsExactly("level", "anna");
    }
}
