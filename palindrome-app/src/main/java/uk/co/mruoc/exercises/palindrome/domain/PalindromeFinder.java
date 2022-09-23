package uk.co.mruoc.exercises.palindrome.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Slf4j
public class PalindromeFinder {

    private final FilterFunction filterFunction;

    public PalindromeFinder() {
        this(new OnlyLongestFilterFunction());
    }

    public Collection<String> findPalindromes(PalindromeRequest request) {
        String input = request.getInput();
        log.info("checking if {} contains a palindrome", input);
        Collection<String> palindromes = calculateAllUniqueSubstringsLongestFirst(request)
                .map(PalindromeFinder::isPalindrome)
                .flatMap(Optional::stream)
                .toList();
        return filterFunction.apply(palindromes);
    }

    private Stream<String> calculateAllUniqueSubstringsLongestFirst(PalindromeRequest request) {
        String sanitized = sanitize(request.getInput());
        Set<String> substrings = new HashSet<>();
        int length = sanitized.length();
        for (int start = 0; start < length; start++) {
            for (int end = start + 1; end <= length; ++end) {
                if (end - start >= request.getMinLength()) {
                    substrings.add(sanitized.substring(start, end));
                }
            }
        }
        return substrings.stream();
    }

    private static Optional<String> isPalindrome(String input) {
        String reversed = reverse(input);
        boolean result = input.equals(reversed);
        log.info("substring {} is palindrome {}", input, result);
        if (result) {
            return Optional.of(input);
        }
        return Optional.empty();
    }

    private static String sanitize(String input) {
        String sanitized = StringUtils.deleteWhitespace(input);
        log.info("sanitized {} to {}", input, sanitized);
        return sanitized;
    }

    private static String reverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }
}
