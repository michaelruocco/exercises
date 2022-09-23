package uk.co.mruoc.exercises.palindrome.domain;

import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.palindrome.domain.filter.FilterFunction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Slf4j
public class RemoveDuplicatesFilterFunction implements FilterFunction {

    @Override
    public Collection<String> apply(Collection<String> inputs) {
        Collection<String> deduped = new ArrayList<>();
        inputs.stream()
                .sorted(new DescendingStringLengthComparator())
                .filter(input -> !isDuplicate(deduped, input))
                .forEach(deduped::add);
        return Collections.unmodifiableCollection(deduped);
    }

    private static boolean isDuplicate(Collection<String> substrings, String nextSubstring) {
        boolean duplicate = substrings.stream().anyMatch(s -> s.contains(nextSubstring));
        log.info("duplicate {} for {} against {}", duplicate, nextSubstring, substrings);
        return duplicate;
    }
}
