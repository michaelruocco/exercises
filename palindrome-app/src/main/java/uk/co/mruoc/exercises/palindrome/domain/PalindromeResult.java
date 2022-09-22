package uk.co.mruoc.exercises.palindrome.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.Collections;

@Builder
@Data
@JsonDeserialize(using = PalindromeResultDeserializer.class)
public class PalindromeResult {

    private final String input;
    @Builder.Default
    private final Collection<String> palindromes = Collections.emptyList();

    public Collection<String> getPalindromes() {
        return palindromes;
    }
}
