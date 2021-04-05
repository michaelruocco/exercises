package uk.co.mruoc.exercises.palindrome.domain;

public class PalindromeCalculator {

    public PalindromeResult isPalindrome(String input) {
        String reversed = reverse(input);
        return PalindromeResult.builder()
                .input(input)
                .reversed(reversed)
                .palindrome(input.equals(reversed))
                .build();
    }

    private String reverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }

}
