package uk.co.mruoc.exercises.palindrome.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.exercises.palindrome.domain.filter.Filter;

import java.util.Collection;

@Builder
@RequiredArgsConstructor
@Data
@JsonDeserialize(using = ApiPalindromeResponseDeserializer.class)
public class ApiPalindromeResponse {

    private final String input;
    private final int minLength;
    private final Filter filter;
    private final Collection<String> palindromes;
}
