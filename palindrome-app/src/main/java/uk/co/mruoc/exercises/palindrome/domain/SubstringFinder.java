package uk.co.mruoc.exercises.palindrome.domain;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@Slf4j
public class SubstringFinder {

    public Stream<String> findSubstrings(String input, int minLength) {
        String sanitized = sanitize(input);
        Set<String> substrings = new HashSet<>();
        int length = sanitized.length();
        for (int start = 0; start < length; start++) {
            for (int end = start + 1; end <= length; ++end) {
                if (end - start >= minLength) {
                    substrings.add(sanitized.substring(start, end));
                }
            }
        }
        return substrings.stream();
    }

    private static String sanitize(String input) {
        String sanitized = StringUtils.deleteWhitespace(input);
        log.info("sanitized {} to {}", input, sanitized);
        return sanitized;
    }
}
