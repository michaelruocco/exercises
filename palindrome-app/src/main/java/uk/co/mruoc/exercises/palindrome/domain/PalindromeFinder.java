package uk.co.mruoc.exercises.palindrome.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.palindrome.domain.filter.Filter;
import uk.co.mruoc.exercises.palindrome.domain.filter.FilterFunction;
import uk.co.mruoc.exercises.palindrome.domain.filter.FilterFunctionFactory;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class PalindromeFinder {

    private final SubstringFinder substringFinder;
    private final FilterFunctionFactory filterFactory;

    public PalindromeFinder() {
        this(new SubstringFinder(), new FilterFunctionFactory());
    }

    public Collection<String> findPalindromes(PalindromeRequest request) {
        String input = request.getInput();
        int minLength = request.getMinLength();
        log.info("checking if {} contains a palindromes with min length {}", input, minLength);
        Collection<String> palindromes = substringFinder.findSubstrings(input, minLength)
                .map(PalindromeFinder::isPalindrome)
                .flatMap(Optional::stream)
                .toList();
        return filter(palindromes, request.getFilter());
    }

    private Collection<String> filter(Collection<String> palindromes, Filter filter) {
        FilterFunction filterFunction = filterFactory.toFunction(filter);
        return filterFunction.apply(palindromes);
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

    private static String reverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }
}
